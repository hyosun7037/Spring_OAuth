package com.cos.securityex01.test;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.securityex01.model.User;
import com.cos.securityex01.repository.UserRepository;

@RestController
public class OptionalControllerTest {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/test/user/{id}")
	public User 옵셔널유저찾기(@PathVariable int id){
		
		//1번 방법 isPresent
//		Optional<User> userOptional = userRepository.findById(id); // 리턴타입이 null이라서 오류가 나는것, null일 수도 있어서 위험함
//		User user;
//		if(userOptional.isPresent()) { // null값 아니면 출력
//			user = userOptional.get(); 
//		}else {
//			user = new User();
//		}
		
		// 2번 방법 orElseGet
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//
//			@Override
//			public User get() { // 리턴을 할 건데 값이 있으면 바로 리턴, 없으면 빈 객체 리턴!
//				return new User();
//			}
//		});
		
		// 3번 방법 orElseGet + 화살표 함수
//		User user = userRepository.findById(id).orElseGet(()-> { // 함수 타입을 모를 때, 화살표 함수 적기!
//				return User.builder().id(5).username("아무개").email("아무개@naver.com").build();
//		});
		
		//4번 방법 orElseThrow
		User user = userRepository.findById(id)
					.orElseThrow(()-> { //null 아니면 illeger // 이렇게 화살표 함수를 쓰면 안에 타입을 몰라도 사용 가능!!
							return new NullPointerException("값이없음!!"); // 리턴할 때 NullPointerException, 처리값은 controller로 넘기기
					}); 
		return user;
	}
	// optional자체는 null이 아님
	// class Container<T>{T data}; 만약 user로 파싱했다고 가정, container<User> c = new Container(); c는 절대 null이 아님
	// c.setData(user)를 넣어줌. 리턴값은 c, c는 get / orElseGet() / orElesThrow() / isPresent() 함수를 가지고 있음
	// c.get()하면 안에 있는 user가 튀어나옴, null이 아닐때만 사용하기. 
	// c.ElseGet()은 하나라도 만들어서 리턴시키라는 의미
	// c.orElseThrow()은 정상적인 객체면 그대로 리턴, null이면 throw값
	// c.isPresent() 불리언값
}
