package partOne;

public class Country {
    private String name;
    private double area;
    private long population;
    private String capitalName;
    private long capitalPopulation;

    public Country(String name, double area, long population, String capitalName, long capitalPopulation) {
        validateName(name);
        this.name = name;
        validateArea(area);
        this.area = area;
        validatePopulation(population);
        this.population = population;
        validateCapitalName(capitalName);
        this.capitalName = capitalName;
        validateCapitalPopulation(capitalPopulation);
        this.capitalPopulation = capitalPopulation;
    }

    public Country(String name, double area, long population) {
        validateName(name);
        this.name = name;
        validateArea(area);
        this.area = area;
        validatePopulation(population);
        this.population = population;
        this.capitalName = null;
        this.capitalPopulation = 0;
    }

    public Country() {
    }

    public String getName() {
        return name;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название страны не может быть пустым");
        }
    }

    private void validateArea(Double area) {
        if (area <= 0) {
            throw new IllegalArgumentException("Площадь должна быть строго больше нуля");
        }
    }

    private void validatePopulation(Long population) {
        if (population < 0) {
            throw new IllegalArgumentException("Население не может быть отрицательным");
        }
    }

    private void validateCapitalName(String capitalName) {
        if (capitalName == null) {
            throw new IllegalArgumentException("Название столицы не может быть пустым");
        }
    }

    private void validateCapitalPopulation(Long capitalPopulation) {
        if (capitalPopulation <= 0) {
            throw new IllegalArgumentException("Население столицы не может быть отрицательным");
        }
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        validateArea(area);
        this.area = area;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        validatePopulation(population);
        this.population = population;
    }

    public double getPopulationDensity() {
        if (population <= 0 || area <= 0) {
            throw new IllegalStateException("Невозможно вычислить плотность: некорректные данные");
        }
        return (double) population / area;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public long getCapitalPopulation() {
        return capitalPopulation;
    }

    public void setCapital(String capitalName, long capitalPopulation) {
        if (capitalName == null) {
            this.capitalName = null;
            this.capitalPopulation = 0;
        } else if (capitalPopulation < 0) {
            throw new IllegalArgumentException("Население столицы не может быть отрицательным");
        } else {
            this.capitalPopulation = capitalPopulation;
            this.capitalName = capitalName;
        }
    }

    @Override
    public String toString() {
        String country = name + ": " + area + " km²";
        if (population > 0) {
            country = country + ", население: " + population;
        }
        if (capitalName != null && capitalPopulation > 0) {
            return country + '\n' +
                    "Столица: " + capitalName +
                    ", население: " + capitalPopulation;
        }
        return country;
    }
}
