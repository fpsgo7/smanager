document.querySelector('div.container-fluid>div:nth-of-type(2)').remove();
document.querySelector('div.container-fluid>table').remove();

// json 문자열
// 자바 스크립트 객체 {속성: 값, 속성:값,...} new Object()'
let javascriptObj = {name: '홍길동', age: 20} // -> {"name" : "홍길동","age":20}
let json = JSON.stringify(javascriptObj); // obj 를 위 주석처럼 json 화 시킨다.
// 자바스크립트 객체를 json 화 시킨대상을 출력시키면
// {"name" : "홍길동","age":20} 출력된다.
console.log(json);

javascriptObj = {name: '홍길동', age: 20,pets:[
	{name:'야옹이', age: 3},
	{name:'멍멍이', age: 2}
]};
json = JSON.stringify(javascriptObj);
console.log(json);
// json을 다시 자바스크립트 객체로
let obj1 = JSON.parse(json);
console.log(obj1);
