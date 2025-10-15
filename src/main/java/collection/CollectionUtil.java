package collection;

import data.City;


public class CollectionUtil {
    static Validator validator = new Validator();
        public boolean checkExist(Long ID) {
            for (City city:CollectionManager.getCollection()) {
                if (city.getId().equals(ID))
                    return true;
            }
            return false;
        }

        public static void display(City city) {
            System.out.print("_________________________________________________________\n");
            System.out.println("ID коллекции: " + city.getId());
            System.out.println("Название города: " + city.getName());
            System.out.println("Координата X: " + city.getCoordinates().getX() + " СШ");
            System.out.println("Координата Y: " + city.getCoordinates().getY() + " ЮШ");
            System.out.println("Дата создания: " + city.getCreationDate());
            System.out.println("Площадь: " + city.getArea() + " км^2");
            System.out.println("Население: " + city.getPopulation() + " ч");
            System.out.println("Высота над уровнем моря: " + city.getMetersAboveSeaLevel() + " м");
            System.out.println("Климат: " + city.getClimate().name());
            System.out.println("Политические убеждения: " + city.getGovernment().name());
            System.out.println("Стандарт жизни: " + city.getStandardOfLiving().name());
//            System.out.println("Правитель: " + city.getGovernor());
            System.out.println("_________________________________________________________\n");
        }

        public boolean checkIfCorrect(City city){
            if (
                        validator.checkName(city.getName()) ||
                        validator.checkCoordinateX(city.getCoordinates().getX()) ||
                        validator.checkCoordinateY(city.getCoordinates().getY()) ||
                        validator.checkArea(city.getArea()) ||
                        validator.checkPopulation(city.getPopulation()) ||
                        validator.checkMetersAboveSeaLevel(city.getMetersAboveSeaLevel()) ||
                        validator.checkClimate(city.getClimate()) ||
                        validator.checkGovernment(city.getGovernment()) ||
                        validator.checkStandardOfLiving(city.getStandardOfLiving())
//                        validator.checkGovernor(city.getGovernor())
            ){
                return true;
            }
            return false;
        }
    }

