package hello.core.singleton;

public class SingletonService {

    //static으로 자바가 뜰 때 클래스 레벨에 공용 객체를 딱 하나 생성한다.
    private static final SingletonService instance = new SingletonService();

    // public static으로 전역 공개한 이유 :
    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("singleton 객체 호출");
    }

}
