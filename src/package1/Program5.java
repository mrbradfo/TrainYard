// Matt Bradford Train Yard Program
//This program manages trains in a train yard.
package package1;

import java.util.ArrayList;
import java.util.Scanner;

public class Program5 {
    public static Scanner kb = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("\tWelcome to the Train RailRoad Application\n");

        Contents[] FreightCarContents = initializeContents();
        int mainOption;
        String owner;//, engineer;
        int ID;
        double maxLoad = setMaxLoad();

        Train train = createNewTrain();



        do {

            do {

                System.out.println("\nTrain Building Menu");
                System.out.println("(1) Add a freight car");
                System.out.println("(2) Display a complete description of the Train");
                System.out.println("(3) Display a brief summary of each car");
                System.out.println("(4) Display total weight, value, and number of cars on train");
                System.out.println("(5) Start a new train");
                System.out.println("(6) Exit program");
                System.out.print("Select an option (1-6): ");
                mainOption = kb.nextInt();
                if (mainOption < 1 || mainOption > 6) {
                    System.out.println("Please enter a number between 1 and 6");
                }

            } while (mainOption < 1 || mainOption > 6);

            int carType, contentsOption;
            double density, thickness, width, length, height, radius, topLength, bottomLength, loadFactor;
            if (mainOption == 1) {
                System.out.println("\nTrain Type:");
                System.out.println("(1) Box ");
                System.out.println("(2) Tank ");
                System.out.println("(3) Hopper ");
                System.out.print("Select car type: ");
                carType = kb.nextInt();

                System.out.println();

                System.out.print("Enter the name of the car owner: ");
                owner = kb.next();
                System.out.print("Enter the car's ID#");
                ID = kb.nextInt();
                System.out.print("Enter the base frame weight of the car: ");
                double carBaseWeight = kb.nextDouble();

                System.out.println();

                System.out.print("Enter the Wall Density: ");
                density = kb.nextDouble();
                System.out.print("Enter the Wall Thickness: ");
                thickness = kb.nextDouble();


                System.out.print("Enter the Load Factor as a percent: %");
                loadFactor = kb.nextDouble();

                do {
                    System.out.printf("%-15s\t\t%-25s\t%-50s", "Contents",
                            "Density(Pounds per Cubic Foot)", "Value(Dollars per Pound)");
                    System.out.printf("%-15s%-30s\t%-25s", "\n(1) Oil", "\t\t55", "7.85");
                    System.out.printf("%-15s%-30s\t%-25s", "\n(2) Coal", "\t\t69", "50");
                    System.out.printf("%-15s%-30s\t%-25s", "\n(3) Soybeans", "\t\t47", "2.72");
                    System.out.printf("%-15s%-30s\t%-24s", "\n(4) Linseed, meal", "\t32", "0.07");
                    System.out.printf("%-15s%-30s\t%-25s", "\n(5) Oats", "\t\t27", "1.30");
                    System.out.print("\nSelect Contents (1-5): ");
                    contentsOption = kb.nextInt();
                } while (contentsOption < 1 || contentsOption > 5);

                Contents carContents = FreightCarContents[contentsOption - 1];
                System.out.println("Contents: " + carContents.getname());
//
//                if (contentsOption == 1) {
//                    contents = "Oil";
//                    contentDensity = 55;
//                    contentValue = 7.85;
//                } else if (contentsOption == 2) {
//                    contents = "Coal";
//                    contentDensity = 69;
//                    contentValue = 50;
//                } else if (contentsOption == 3) {
//                    contents = "Soybeans";
//                    contentDensity = 47;
//                    contentValue = 2.72;
//                } else if (contentsOption == 4) {
//                    contents = "Linseed, meal";
//                    contentDensity = 32;
//                    contentValue = 0.07;
//                } else if (contentsOption == 5) {
//                    contents = "Oats";
//                    contentDensity = 27;
//                    contentValue = 1.30;
//                }
//
//                Contents carContents = new Contents(contents, contentDensity, contentValue);


                FreightCar car = null;
                if (carType == 1) {
                    System.out.println("\nEnter the Dimensions of the Box car");
                    System.out.print("Enter the Width: ");
                    width = kb.nextDouble();
                    System.out.print("Enter the Length: ");
                    length = kb.nextDouble();
                    System.out.print("Enter the Height:");
                    height = kb.nextDouble();
                    Rectangle container = new Rectangle(thickness, density, length, width, height);
                    car = new FreightCar(carContents, container, loadFactor, owner, carBaseWeight, ID);
                } else if (carType == 2) {
                    System.out.println("\nEnter the Dimensions of the Tank car");
                    System.out.print("Enter the Radius: ");
                    radius = kb.nextDouble();
                    System.out.print("Enter the Length: ");
                    length = kb.nextDouble();
                    Cylinder container = new Cylinder(thickness, density, radius, length);
                    car = new FreightCar(carContents, container, loadFactor, owner, carBaseWeight, ID);

                } else if (carType == 3) {
                    System.out.println("\nEnter the Dimensions of the Hopper car");
                    System.out.print("Enter the upper length: ");
                    topLength = kb.nextDouble();
                    System.out.print("Enter the bottom length: ");
                    bottomLength = kb.nextDouble();
                    System.out.print("Enter the width: ");
                    width = kb.nextDouble();
                    System.out.print("Enter the height: ");
                    height = kb.nextDouble();
                    Trapezoid container = new Trapezoid(thickness, density, width, height, topLength, bottomLength);
                    car = new FreightCar(carContents, container, loadFactor, owner, carBaseWeight, ID);
                }
                System.out.println(car);

                train.addFreightCar(car);


            } else if (mainOption == 2) {

                System.out.println(train);

            } else if (mainOption == 3) {
                train.briefSummary(maxLoad);
            } else if (mainOption == 4) {
                train.displayTotals();
            } else if (mainOption == 5) {
                train = createNewTrain();
            }

        } while (mainOption != 6);

        System.out.println("Good Bye!");
    }


    private static double setMaxLoad() {
        double maxLoad;//= 0;
            System.out.print("Enter maximum acceptable load: ");
            maxLoad = kb.nextDouble();

            return maxLoad;

    }

    private static Contents[] initializeContents() {

        Contents[] contentsArray = new Contents[5];
        contentsArray[0] = new Contents("Oil", 55, 7.85);
        contentsArray[1] = new Contents("Coal", 69, 50);
        contentsArray[2] = new Contents("Soybeans", 47, 2.72);
        contentsArray[3] = new Contents("Linseed, meal", 32, 0.07);
        contentsArray[4] = new Contents("Oats", 27, 1.30);
        return contentsArray;
    }

    private static Train createNewTrain() {

        double trainBaseWeight, pullingCapacity;
        String owner, engineer;
        int ID;
        System.out.print("Enter the owner of the Train: ");
        owner = kb.next();
        System.out.print("Enter the trains Engineer: ");
        engineer = kb.next();
        System.out.print("Enter the ID of the Train: ");
        ID = kb.nextInt();
        System.out.print("Enter the Base Frame Weight: ");
        trainBaseWeight = kb.nextDouble();
        System.out.print("Enter the pulling capacity of the Train: ");
        pullingCapacity = kb.nextDouble();

        Engine engine = new Engine(owner, trainBaseWeight, ID, pullingCapacity);
        Train train = new Train(engineer, engine);

        return train;
    }

}

abstract class RollingStock {
    private String _owner;
    private int _id;
    private double _baseWeight;

    RollingStock(String owner, double baseWeight, int id) {
        _owner = owner;
        _baseWeight = baseWeight;
        _id = id;
    }

    public String getOwner() {
        return _owner;
    }

    public int getId() {
        return _id;
    }

    public double getBaseWeight() {
        return _baseWeight;
    }

    public String toString() {
        return //"\n\nTrain cars: \n" +
                "\nOwner: " + _owner +
                "\nID: #" + _id +
                "\nBase Frame Weight: " + _baseWeight;
    }
}


class Engine extends RollingStock {
    private double _pullingCapacity;

    public Engine(String owner, double baseWeight, int id, double pullingCapacity) {
        super(owner, baseWeight, id);
        _pullingCapacity = pullingCapacity;
    }


    public double getPullingCapacity() {
        return _pullingCapacity;
    }


    public String toString() {
        return super.toString() +
                "\nPulling Capacity: " + _pullingCapacity;
    }
}

class Train {
    private String _engineer;
    private Engine _engine;
    private ArrayList<FreightCar> carList;

    public Train(String engineer, Engine engine) {
        _engineer = engineer;
        _engine = engine;
        carList = new ArrayList<FreightCar>();
    }

    public double calculateTrainWeight() {
        double totalWeight = 0;

        for (int i = 0; i < carList.size(); i++) {
            FreightCar car = carList.get(i);
            //System.out.println("Car " + i + " weight: " + car.calculateWeight());
            totalWeight += car.calculateWeight();
        }

        return totalWeight + _engine.getBaseWeight();
    }

    public void displayTotals() {

        System.out.println("\nTrain Values:");
        System.out.printf("Total Weight: %-5.2f", + calculateTrainWeight());
        if(calculateTrainWeight() > _engine.getPullingCapacity() )
            System.out.print("  ----- Total weight exceeds pulling capacity -----");
        System.out.printf("\nTotal Value: $%-5.2f", + calculateTrainValue());
        System.out.println("\nNumber of cars: " +  numOfCars());
    }

    public double calculateTrainValue() {
        double totalValue = 0;

        for (FreightCar car : carList) {
            totalValue += car.calculateValue();
        }
        return totalValue;
    }

    public String getengineer() {
        return _engineer;
    }

    private int numOfCars() {
        return carList.size();
    }

    public void addFreightCar(FreightCar car) {
        carList.add(car);
    }

    public void deleteFreightCar(int id) {

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getId() == id) {
                carList.remove(i);
            }
        }
        carList.trimToSize();
    }

    public void changeLoadFactor(int id, double newLoad) {
        for (FreightCar car : carList) {
            if (car.getId() == id) {
                car.setloadFactor(newLoad);
            }
        }

    }


    public void briefSummary(double maxLoad) {
        for (int i = 0; i < carList.size(); i++) {
            System.out.println("\nID: #" + carList.get(i).getId());

            System.out.printf("Total Weight: %-5.2f", carList.get(i).calculateWeight());
            if(carList.get(i).calculateWeight() > maxLoad)
                System.out.print("  ----- Weight of car exceeds maximum load -----");
            System.out.printf("\nTotal Value: $%-5.2f", carList.get(i).calculateValue());
            System.out.println();
        }

    }

    public String toString() {
        String s = "\nTrain:" +  "\nEngineer name: " + _engineer + _engine.toString();
        s += "\n\nTrain cars:";
        for (FreightCar car : carList) {
            s += car.toString();
        }
        return s;
    }
}

class FreightCar extends RollingStock {
    private Contents _contents;
    private Container _container;
    private double _loadFactor;

    public FreightCar(Contents contents, Container container, double loadFactor,
                      String owner, double baseWeight, int id) {
        super(owner, baseWeight, id);
        _contents = contents;
        _container = container;
        _loadFactor = loadFactor;
    }

    public double calculateWeight() {
        double weight;
        weight =  weightOfContents();//_contents.getcontentDensity() * _container.calculateInteriorVolume() * (getloadFactor() / 100.0);
        weight += getBaseWeight();
        weight += _container.weightOfWall();
//        weight += (_container.getWallDensty() *
//                (_container.calculateExteriorVolume() - _container.calculateInteriorVolume()));
        return weight;
    }

    public double calculateValue() {
        double value = weightOfContents() * _contents.getvalue();

        return value;
    }

    private double weightOfContents() {
        double amtOfContents = _contents.getcontentDensity() * _container.calculateInteriorVolume() *
                (getloadFactor() / 100.0);
        return amtOfContents;
    }

    public double getloadFactor() {
        return _loadFactor;
    }

    public void setloadFactor(double loadFactor) {
        _loadFactor = _loadFactor;
    }

    public String toString() {
        return super.toString()      +
                _contents.toString() +
                "\nContainer: "      +
                _container.toString() +
                "\nLoad factor: " + _loadFactor + "%";
    }
}

class Contents {
    private String _name;
    private double _contentDensity;
    private double _value;

    public Contents(String name, double contentDensity, double value) {
        _name = name;
        _contentDensity = contentDensity;
        _value = value;
    }

    public String getname() {
        return _name;
    }

    public double getcontentDensity() {
        return _contentDensity;
    }

    public double getvalue() {
        return _value;
    }

    public String toString() {
        return "\nContents: "
                + _name + "\n"
                + "Density: " + _contentDensity + "\n"
                + "Value: " + _value;
    }
}


abstract class Container {
    private double _wallThickness;
    private double _wallDensity;

    public Container(double wallThickness, double wallDensity) {
        _wallThickness = wallThickness;
        _wallDensity = wallDensity;
    }

    abstract public double calculateInteriorVolume();

    abstract public double calculateExteriorVolume();

    public double weightOfWall() {
        double weight = 0;
        weight += (getWallDensty() *
                (calculateExteriorVolume() - calculateInteriorVolume()));
        return weight;
    }

    public double getWallThickness() {
        return _wallThickness;
    }

    public double getWallDensty() {
        return _wallDensity;
    }

    public String toString() {
        return "\nWall Thickness: " + _wallThickness +
                "\nWall Density: " + _wallDensity;
    }
}

class Trapezoid extends Container {
    private double _width;
    private double _height;
    private double _topLength;
    private double _bottomLength;


    public Trapezoid(double wallThickness, double wallDensity, double width, double height,
                     double topLength, double bottomLength) {
        super(wallThickness, wallDensity);
        _width = width;
        _height = height;
        _topLength = topLength;
        _bottomLength = bottomLength;
    }


    public double getWidth() {
        return _width;
    }

    public double getHeight() {
        return _height;
    }

    public double getTopLength() {
        return _topLength;
    }

    public double getBottomLength() {
        return _bottomLength;
    }


    public double calculateExteriorVolume() {
        double volume;
        volume = (.5) * ((getTopLength() + getBottomLength()) * getWidth() * getHeight());
        return volume;
    }

    public double calculateInteriorVolume() {
        double volume = (.5) * ((getTopLength() - (2 * getWallThickness())) +
                (getBottomLength() - (2 * getWallThickness()))) * (getWidth() - (2 * getWallThickness()))
                        * (getHeight() - getWallThickness());
        return volume;
    }


    public String toString() {
        return  super.toString() +
                "\nCar Type: Hopper" +
                "\nWidth: " + _width +
                "\nHeight: " + _height +
                "\nUpper Length: " + _topLength +
                "\nLower Length: " + _bottomLength;
    }
}

class Rectangle extends Container {
    private double _length;
    private double _width;
    private double _height;

    public Rectangle(double wallThickness, double wallDensity, double length, double width, double height) {
        super(wallThickness, wallDensity);
        _length = length;
        _width = width;
        _height = height;
    }

    public double calculateExteriorVolume() {
        double volume;
        volume = getLength() * getWidth() * getWidth();
        return volume;
    }

    public double calculateInteriorVolume() {
        double volume = (getLength() - (2 * getWallThickness())) * (getWidth() - (2 * getWallThickness()))
                * (getHeight() - (2 * getWallThickness()));
        return volume;
    }

    public double getLength() {
        return _length;
    }

    public double getWidth() {
        return _width;
    }

    public double getHeight() {
        return _height;
    }


    public String toString() {
        return super.toString() +
                "\nCar Type: Box" +
                "\nLength: " + _length +
                "\nWidth: " + _width +
                "\nHeight: " + _height;
    }
}

class Cylinder extends Container {
    private double _radius;
    private double _length;

    public Cylinder(double wallThickness, double wallDensity, double radius, double length) {
        super(wallThickness, wallDensity);
        _radius = radius;
        _length = length;
    }

    public double getRadius() {
        return _radius;
    }

    public double getLength() {
        return _length;
    }

    public double calculateExteriorVolume() {
        double volume;
        volume = Math.PI * Math.pow((getRadius()), 2) * (getLength());
        return volume;
    }

    public double calculateInteriorVolume() {
        double volume = Math.PI * Math.pow((getRadius() - getWallThickness()), 2) * (getLength() - (2 * getWallThickness()));
        return volume;
    }

    public String toString() {
        return super.toString() +
                "\nCar Type: Tank" +
                "\nRadius: " + _radius +
                "\nLength: " + _length;
    }
}
