package hello.core.member;

public class Member {

    /**
     * 만약 생성자가 없는 상황에서 final 키워드로 고정하지 않으면 이미 생성한 Member 객체의 값이 새로운 값으로 생성할 때 새로운 값으로 덮어쓸 수 있다.
     * 지금은 생성자를 따로 두었기 떄문에 final로 고정하지 않아도 기존에 생성된 객체 안 데이터들을 건드리지 않는다.
     */
    private Long id;
    private String name;
    private Grade grade;

    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
