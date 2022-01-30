
public class Loader
{
    private static Cat getKitten()
    {
        Cat kitten = new Cat(1100.0);
        return kitten;
    }

    public static void main(String[] args)
    {
        Cat cat6 = getKitten();
        Cat cat7 = getKitten();
        Cat cat8 = getKitten();
        System.out.println("Weight cat6 = "+cat6.getWeight());
        System.out.println("Weight cat7 = "+cat7.getWeight());
        System.out.println("Weight cat8 = "+cat8.getWeight());

        Cat cat = new Cat();
        cat.setColor(CatColor.RED_COLOR);
        cat.setName("Васька");
        System.out.println(cat.getColor());

        Cat cat9 = Cat.copy(cat);
        //new Cat(cat.getWeight(),cat.getName(),cat.getColor());
        System.out.println("cat9 weight="+cat9.getWeight()+" name="+cat9.getName()+" color="+cat9.getColor());
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();

        System.out.println("cat1="+cat1.getWeight());
        cat1.meow();
        System.out.println("cat1 after meow="+cat1.getWeight());
        System.out.println("cat2="+cat2.getWeight());
        System.out.println("cat3="+cat3.getWeight());
        System.out.println("cat4="+cat4.getWeight());
        System.out.println("cat5="+cat4.getWeight());
        System.out.println("Count = "+ Cat.getCount());

        System.out.println("cat before feed = "+cat.getWeight());
        while(!cat.getStatus().equals("Exploded"))
        {
            cat.feed(10.0);
        }
        System.out.println("cat after feed="+cat.getStatus());
        System.out.println("sum feed = "+cat.getSumFeed());
        cat.pee();
        System.out.println("sum feed after pee = "+cat.getSumFeed());

        while(!cat1.getStatus().equals("Dead"))
        {
            cat1.meow();
        }
        cat1.meow();
        System.out.println(cat1.getStatus());
        System.out.println("Cat Color = "+CatColor.BLACK_COLOR);
        System.out.println("Count="+Cat.getCount());
    }
}