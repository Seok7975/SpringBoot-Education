package com.edu.springboot.bean1;

//데이터 저장 기능만 있는 일반적인 VO 클래스 정의
public class Person {
	//멤버정의 
	private String name;
	private int age;
	private Notebook notebook;//객체형 멤버변수
	
	//기본생성자 및 인수생성자 정의
	public Person() {}
	public Person(String name, int age, Notebook notebook) {
		super();
		this.name = name;
		this.age = age;
		this.notebook = notebook;
	}	
	
	//getter/setter 메서드 정의 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Notebook getNotebook() {
		return notebook;
	}
	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}
	
	/* toString() 메서드 오버라이딩. 별도의 출력메서드 없이 인스턴스를 그대로
	print문에 사용할 수 있다. */
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", notebook=" + notebook + "]";
	}
}


