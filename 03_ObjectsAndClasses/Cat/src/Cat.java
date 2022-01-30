
public class Cat
{
    private CatColor color;
    public static int count = 0;
    public static final int COUNT_EYES = 2;
    public static final int MAX_WEIGHT = 9000;
    public static final int MIN_WEIGHT = 1000;

    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;
    private double sumFeed;
    private String name;

    public void setColor(CatColor color)
    {
        this.color=color;
    }

    public CatColor getColor()
    {
        return this.color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = MIN_WEIGHT;
        maxWeight = MAX_WEIGHT;
        count++;
    }

    public Cat(double weight)
    {
        this();
        this.weight = weight;
        this.originWeight = weight;
    }

    public Cat(double weight, String name, CatColor color)
    {
        this();
        this.weight = weight;
        this.name = name;
        this.color = color;
    }
    public static Cat copy(Cat original)
    {
        return new Cat(original.getWeight(), original.getName(), original.getColor());
    }

    public static int getCount()
    {
        return count;
    }

    public void meow()
    {
        if (getStatus().equals("Exploded") || getStatus().equals("Dead"))
        {
            System.out.println("No meow");
            return;
        }
        weight = weight - 1;
        System.out.println("Meow");
        if (weight < minWeight){
            count = count - 1;
        }
    }

    public void pee()
    {
        if (getStatus().equals("Exploded") || getStatus().equals("Dead"))
        {
            System.out.println("No pee");
            return;
        }
        weight = weight - 1;
        System.out.println("Pee");
        sumFeed = sumFeed - 1;
        if (weight < minWeight){
            count = count - 1;
        }
    }

    public void feed(Double amount)
    {
        if (getStatus().equals("Exploded") || getStatus().equals("Dead"))
        {
            System.out.println("No feed");
            return;
        }
        weight = weight + amount;
        sumFeed = sumFeed + amount;
        if (weight>maxWeight){
            count = count - 1;
        }
    }

    public double getSumFeed()
    {
        return sumFeed;
    }

    public void drink(Double amount)
    {
        if (getStatus().equals("Exploded") || getStatus().equals("Dead"))
        {
            System.out.println("No drink");
            return;
        }
        weight = weight + amount;
        if (weight>maxWeight){
            count = count - 1;
        }
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }
}