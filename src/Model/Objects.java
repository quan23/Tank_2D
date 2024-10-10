package Model;

import java.awt.Graphics;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Image;

public abstract class Objects {
    protected int X,Y,SizeX,SizeY,Layer,Health,MHealth,Damage;

    public int getDamage() {
        return Damage;
    }
    protected ImageIcon Icon;
    
    public Objects(int x, int y, String IconLink,int layer) {
        this.X = x;
        this.Y = y;
        Icon = new ImageIcon(new ImageIcon(IconLink).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
        Layer = layer;
    }
    public Objects(int x, int y, String IconLink) {
        this.X = x;
        this.Y = y;
        Icon = new ImageIcon(IconLink);
        Layer=0;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getLayer() {
        return Layer;
    }

    public void setLayer(int Layer) {
        this.Layer = Layer;
    }

    public ImageIcon getIcon() {
        return Icon;
    }

    public void setIcon(ImageIcon Icon) {
        this.Icon = Icon;
    }
    
    public boolean damageObj(Objects obj) {
        Health-=obj.getDamage();
        return Health >= 0;
    }
}
