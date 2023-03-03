package be.dcharmonie.dartstournament.brackets.draw;

import java.util.ArrayList;
import java.util.List;

import be.dcharmonie.dartstournament.brackets.draw.layout.BracketBoxShape;
import be.dcharmonie.dartstournament.brackets.draw.layout.LineShape;
import be.dcharmonie.dartstournament.brackets.draw.layout.Shape;

/**
 *
 */
public class FinalNodeDraw implements Drawable {
    private static final List<Shape> SHAPES = new ArrayList<>();
    private int x;
    private int y;

    @Override
    public void assembleImage(int x, int y) {
        this.x = x;
        this.y = y;
        SHAPES.add(new BracketBoxShape(this.x, this.y));
        SHAPES.add(new LineShape(this.x - (Shape.WIDTH / 2), this.y, this.x - (Shape.WIDTH / 2) - Shape.WIDTH_LINE, this.y));
        SHAPES.add(new LineShape(this.x + (Shape.WIDTH / 2), this.y, this.x + (Shape.WIDTH / 2) + Shape.WIDTH_LINE, this.y));
    }

    @Override
    public boolean isLeftBracket() {
        throw new UnsupportedOperationException("A final is always in the center");
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public List<Shape> getShapes() {
        return SHAPES;
    }

}
