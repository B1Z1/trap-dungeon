package util.state;

import util.listener.RenderListener;
import util.listener.UpdateListener;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class State implements UpdateListener, RenderListener, KeyListener, MouseListener, MouseMotionListener {
}
