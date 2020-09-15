package basic.tech.dbscan;
import org.apache.commons.math.stat.clustering.Clusterable;
import org.apache.commons.math.util.MathUtils;

import java.util.Collection;


/**
 * @author ： luolm
 * @date ：Created in 2020/9/15
 * @description： //TODO
 */
public class CustomerPoint implements Clusterable<CustomerPoint> {


    private String sender;
    private String sender_addr;
    private int value;
    private final double[] point;


    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getSender_addr() {
        return sender_addr;
    }
    public void setSender_addr(String sender_addr) {
        this.sender_addr = sender_addr;
    }

    public CustomerPoint(final double[] point) {
        this.point = point;
    }

    public double[] getPoint() {
        return point;
    }

    @Override
    public double distanceFrom(final CustomerPoint p) {
        return MathUtils.distance(point, p.getPoint());
    }

    @Override
    public CustomerPoint centroidOf(final Collection<CustomerPoint> points) {
        double[] centroid = new double[getPoint().length];
        for (CustomerPoint p : points) {
            for (int i = 0; i < centroid.length; i++) {
                centroid[i] += p.getPoint()[i];
            }
        }
        for (int i = 0; i < centroid.length; i++) {
            centroid[i] /= points.size();
        }
        return new CustomerPoint(centroid);
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof CustomerPoint)) {
            return false;
        }
        final double[] otherPoint = ((CustomerPoint) other).getPoint();
        if (point.length != otherPoint.length) {
            return false;
        }
        for (int i = 0; i < point.length; i++) {
            if (point[i] != otherPoint[i]) {
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString() {
        final StringBuffer buff = new StringBuffer("{");
        final double[] coordinates = getPoint();
        buff.append("lat:"+coordinates[0]+",");
        buff.append("lng:"+coordinates[1]+",");
        buff.append("value:"+this.getValue());
        buff.append("}");
        return buff.toString();
    }
}