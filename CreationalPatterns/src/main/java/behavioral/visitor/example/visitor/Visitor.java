package behavioral.visitor.example.visitor;

import behavioral.visitor.example.shapes.Circle;
import behavioral.visitor.example.shapes.CompoundShape;
import behavioral.visitor.example.shapes.Dot;
import behavioral.visitor.example.shapes.Rectangle;

public interface Visitor {
    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);
}