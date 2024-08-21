// defer 적용대신 여기에 적용하면
// html의 태그들이 준비된 다읍에 실행된다.
document.addEventListener('DOMContentLoaded',function(){
  // 태그를 참고하여 사용하는 문장
})
// jquery 방식
$(document).ready(function(){
  
})

// show 라는 아이디를 가진 객체를 대입한다.
let obj = $('#show');
console.log("$ jqery로 오브젝트 찾기");
console.log(obj);

console.log("자바스크립트로 오브젝트 찾기");
obj = document.getElementById('show');
console.log(obj);

// jquery 객체 vs. dom 객체
console.log("jqery로 클래스 찾기");
let classObj = $('.show');// 가져온 대상이 여러개가 가능해
console.log(classObj);
// jquery 객체 html 삽입방식
$(classObj).eq(0).html('Hello');// 배열접근이 된다.
$(classObj).eq(1).html('World');

// 밑에는 자바스크립트의 dom 객체 html 삽입방식이다.
// classObj[0].innerHTML("Hello");
// classObj[1].innerHTML("World");

// jquery 객체 생성 후 #show의 하위요소로 추가시킨다.
$('#show').append(('<button> 삭제 </button>'));
