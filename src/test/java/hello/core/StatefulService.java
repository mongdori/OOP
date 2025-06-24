package hello.core;

public class StatefulService {

//    private int price; // 공유 필드 (지역변수가 아니라 전역 변수)

    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
//        this.price = price; // 공유 필드를 갱신한다. 그러므로 여기가 문제
        return price;
    }

//    public int getPrice() {
//
//    }
}
