package learnSpringBoot.model;
import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name="tblProduct")
public class Product {
    @Id  // id
//   @GeneratedValue(strategy = GenerationType.AUTO) // auto increment
    // cos thể dùng sequence để thay cái trên
    @SequenceGenerator(
            name="product_sequence",
            sequenceName="product_sequence",
            allocationSize = 1 // increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )

    private Long id;
    @Column(nullable = false, unique = true, length = 300)
    private String productName;
    private int year;
    private Double price;
    private String url;
    //caculated field = trainsient : ko được trong csdl nhưng được tính toán bởi những trường #
    /*
    @Transient
    private int age;

    public int getAge() {
        return Calendar.getInstance().get(Calendar.YEAR) - this.year;
    }
     */

    //default
    public Product(){}
    public Product( String productName, int year, Double price, String url) {
        this.productName = productName;
        this.year = year;
        this.price = price;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }

    // Để định nghĩa xem 2 đối tượng ntn là giống nhau
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return year == product.year && Objects.equals(id, product.id) && Objects.equals(productName, product.productName) && Objects.equals(price, product.price) && Objects.equals(url, product.url);
//    }
}
