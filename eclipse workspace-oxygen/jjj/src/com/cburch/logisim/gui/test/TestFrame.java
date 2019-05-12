/* Copyright (c) 2006, 2009, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */
 
package com.cburch.logisim.gui.test;

import java.io.File;
import java.io.IOException;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import com.cburch.logisim.circuit.Circuit;
import com.cburch.logisim.circuit.Simulator;
import com.cburch.logisim.circuit.SimulatorEvent;
import com.cburch.logisim.circuit.SimulatorListener;
import com.cburch.logisim.gui.menu.LogisimMenuBar;
import com.cburch.logisim.proj.Project;
import com.cburch.logisim.proj.ProjectEvent;
import com.cburch.logisim.proj.ProjectListener;
import com.cburch.logisim.data.TestVector;
import com.cburch.logisim.data.TestException;
import com.cburch.logisim.util.LocaleListener;
import com.cburch.logisim.util.LocaleManager;
import com.cburch.logisim.util.StringUtil;
import com.cburch.logisim.util.WindowMenuItemManager;

public class TestFrame extends JFrame {

    private class WindowMenuManager extends WindowMenuItemManager
            implements LocaleListener, ProjectListener {
        WindowMenuManager() {
            super(Strings.get("logFrameMenuItem"), false);
            project.addProjectListener(this);
        }
        
        public JFrame getJFrame(boolean create) {
            return TestFrame.this;
        }
        
        public void localeChanged() {
            String title = project.getLogisimFile().getDisplayName();
            setText(StringUtil.format(Strings.get("testFrameMenuItem"), title));
        }

        public void projectChanged(ProjectEvent event) {
            if(event.getAction() == ProjectEvent.ACTION_SET_FILE) {
                localeChanged();
            }
        }
    }

    private class MyListener
            implements ActionListener, ProjectListener,
                SimulatorListener, LocaleListener, ModelListener {

	public void testResultsChanged(int numPass, int numFail)
	{
	    pass.setText(StringUtil.format(Strings.get("passMessage"), Integer.toString(numPass)));
	    fail.setText(StringUtil.format(Strings.get("failMessage"), Integer.toString(numFail)));
	    finished = numPass + numFail;
	}

	public void vectorChanged() { }

	public void testingChanged() {
	    if (getModel().isRunning() && !getModel().isPaused()) {
		run.setEnabled(false);
		stop.setEnabled(true);
	    } else if (getModel().getVector() != null && finished != count) {
		run.setEnabled(true);
		stop.setEnabled(false);
	    } else {
		run.setEnabled(false);
		stop.setEnabled(false);
	    }
	    reset.setEnabled(getModel().getVector() != null && finished > 0);
	}

        public void actionPerformed(ActionEvent event) {
            Object src = event.getSource();
            if(src == close) {
                WindowEvent e = new WindowEvent(TestFrame.this,
                        WindowEvent.WINDOW_CLOSING);
                TestFrame.this.processWindowEvent(e);
            } else if(src == load) {
                int result = chooser.showOpenDialog(TestFrame.this);
                if(result != JFileChooser.APPROVE_OPTION) return;
                File file = chooser.getSelectedFile();
                if(!file.exists() || !file.canRead() || file.isDirectory()) {
                    JOptionPane.showMessageDialog(TestFrame.this,
                        StringUtil.format(Strings.get("fileCannotReadMessage"), file.getName()),
                        Strings.get("fileCannotReadTitle"),
                        JOptionPane.OK_OPTION);
                    return;
                }
				try {
					TestVector vec = new TestVector(file);
					finished = 0;
					count = vec.data.size();
					getModel().setVector(vec);
					curFile = file;
					getModel().setPaused(true);
					getModel().start();
				} catch (IOException e) {
							JOptionPane.showMessageDialog(TestFrame.this,
								StringUtil.format(Strings.get("fileCannotParseMessage"), file.getName(), e.getMessage()),
								Strings.get("fileCannotReadTitle"),
								JOptionPane.OK_OPTION);
				}  catch (TestException e) {
							JOptionPane.showMessageDialog(TestFrame.this,
								StringUtil.format(Strings.get("fileWrongPinsMessage"), file.getName(), e.getMessage()),
								Strings.get("fileWrongPinsTitle"),
								JOptionPane.OK_OPTION);
				}
            } else if(src == run) {
				try {
					getModel().start();
				}  catch (TestException e) {
							JOptionPane.showMessageDialog(TestFrame.this,
								StringUtil.format(Strings.get("fileWrongPinsMessage"), curFile.getName(), e.getMessage()),
								Strings.get("fileWrongPinsTitle"),
								JOptionPane.OK_OPTION);
				}
			} else if(src == stop) {
				getModel().setPaused(true);
			} else if(src == reset) {
				getModel().clearResults();
				testingChanged();
			}
        }
        
        public void projectChanged(ProjectEvent event) {
            int action = event.getAction();
            if(action == ProjectEvent.ACTION_SET_STATE) {
                setSimulator(event.getProject().getSimulator(),
                        event.getProject().getCircuitState().getCircuit());
            } else if(action == ProjectEvent.ACTION_SET_FILE) {
                setTitle(computeTitle(curModel, project));
            }
        }
        
        public void localeChanged() {
            setTitle(computeTitle(curModel, project));
	    panel.localeChanged();
            load.setText(Strings.get("loadButton"));
            run.setText(Strings.get("runButton"));
            stop.setText(Strings.get("stopButton"));
            reset.setText(Strings.get("resetButton"));
            close.setText(Strings.get("closeButton"));
	    myListener.testResultsChanged(getModel().getPass(), getModel().getFail());
            windowManager.localeChanged();
        }

        public void propagationCompleted(SimulatorEvent e) {
            //curModel.propagationCompleted();
        }

        public void tickCompleted(SimulatorEvent e) { }

        public void simulatorStateChanged(SimulatorEvent e) { }
    }
    
    private Project project;
    private Simulator curSimulator = null;
    private Model curModel;
    private Map<Circuit, Model> modelMap = new HashMap<Circuit, Model>();
    private MyListener myListener = new MyListener();
    private WindowMenuManager windowManager;
    private int finished, count;
    private File curFile;
    
    private JFileChooser chooser = new JFileChooser();
    private TestPanel panel;
    private JButton load = new JButton();
    private JButton run = new JButton();
    private JButton stop = new JButton();
    private JButton reset = new JButton();
    private JButton close = new JButton();
    private JLabel pass = new JLabel();
    private JLabel fail = new JLabel();

    public TestFrame(Project project) {
        this.project = project;
        this.windowManager = new WindowMenuManager();
        project.addProjectListener(myListener);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setJMenuBar(new LogisimMenuBar(this, project));
        setSimulator(project.getSimulator(), project.getCircuitState().getCircuit());

	chooser.addChoosableFileFilter(chooser.getAcceptAllFileFilter());
	chooser.addChoosableFileFilter(TestVector.FILE_FILTER);
	chooser.setFileFilter(TestVector.FILE_FILTER);
        
	panel = new TestPanel(this);

        JPanel statusPanel = new JPanel();
	statusPanel.add(pass);
	statusPanel.add(fail);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(load);
        buttonPanel.add(run);
        buttonPanel.add(stop);
        buttonPanel.add(reset);
        buttonPanel.add(close);
        load.addActionListener(myListener);
        run.addActionListener(myListener);
        stop.addActionListener(myListener);
        reset.addActionListener(myListener);
        close.addActionListener(myListener);

	run.setEnabled(false);
	stop.setEnabled(false);
	reset.setEnabled(false);

        Container contents = getContentPane();
        panel.setPreferredSize(new Dimension(450, 300));
	contents.add(statusPanel, BorderLayout.NORTH);
        contents.add(panel, BorderLayout.CENTER);
        contents.add(buttonPanel, BorderLayout.SOUTH);

        LocaleManager.addLocaleListener(myListener);
        myListener.localeChanged();
        pack();
    }
    
    public Project getProject() {
        return project;
    }
    
    Model getModel() {
        return curModel;
    }
    
    private void setSimulator(Simulator value, Circuit circuit) {
        if((value == null) == (curModel == null)) {
            if(value == null || value.getCircuitState().getCircuit() == curModel.getCircuit()) return;
        }

        LogisimMenuBar menubar = (LogisimMenuBar) getJMenuBar();
        //menubar.setCircuitState(value, state);

        if(curSimulator != null) curSimulator.removeSimulatorListener(myListener);
        if(curModel != null) curModel.setSelected(false);
	if(curModel != null) curModel.removeModelListener(myListener);

        Model oldModel = curModel;
        Model data = null;
        if(value != null) {
            data = modelMap.get(value.getCircuitState().getCircuit());
            if(data == null) {
                data = new Model(project, value.getCircuitState().getCircuit());
                modelMap.put(data.getCircuit(), data);
            }
        }
        curSimulator = value;
        curModel = data;

        if(curSimulator != null) curSimulator.addSimulatorListener(myListener);
        if(curModel != null) curModel.setSelected(true);
	if(curModel != null) curModel.addModelListener(myListener);
        setTitle(computeTitle(curModel, project));
	if(panel != null)
	    panel.modelChanged(oldModel, curModel);
    }
    
    public void setVisible(boolean value) {
        if(value)
            windowManager.frameOpened(this);
        super.setVisible(value);
    }
    
    private static String computeTitle(Model data, Project proj) {
        String name = data == null ? "???" : data.getCircuit().getName();
        return StringUtil.format(Strings.get("testFrameTitle"), name,
                proj.getLogisimFile().getDisplayName());
    }
}
