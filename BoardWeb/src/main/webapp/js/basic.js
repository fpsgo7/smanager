console.log("연습합시다.");

let name = "이창호";
let address = "대구 100번지";
let age = 15;

/* 객체 사용버전 */
const myInfo = {
	name: name,
	address: address,
	age: age
}

// createElement('span') => <span></span>
// innerText/innerHTML = 값
// 부모요소.appendChild(자식) => <부모><자식/></부모>
makeDom();
function makeDom(){
	for(let prop in myInfo){
		let span = document.createElement('span');
		span.innerHTML = myInfo[prop]+"<br>";
		document.querySelector("#show").appendChild(span);
	}
}
/* 배열 사용버전 */
const myInfoArr = [
	name,address,age
]
makeDomByArr();
function makeDomByArr(){
	myInfoArr.forEach(
		function(item){
			let span = document.createElement('span');
			span.innerHTML = item + "<br>";
			document.querySelector("#show").appendChild(span);
		}
	);
}

/* `` 활용해보기  */
console.log(`이름은 ${name} 주소는 ${address}`);
console.log("${age > 20 ? '성년' : '미성년'}");
console.log(`${age > 20 ? '성년' : '미성년'}`);
console.log(typeof name);

/* 객체값 출력해보기 */
const obj = {name: "홍길동",
			age: 20,
			myInfo: function(){
				return this.name + ', ' + this.age;
				}
			}
console.log(`이름은 ${obj.name} 나이는 ${obj.age} 입니다.`)
console.log(obj.myInfo());
for(let prop in obj){
	console.log(`속성은 ${prop} 이고 값은 ${obj[prop]}`);
}

/*배열 사용해보기 */
console.log("배열 출력하기");
const ary = [1,2,3];
ary.push(4);
ary.unshift(5);
for(let num of ary){
	console.log(num);
}
console.log("2의 배수 와 첫번째 값과 마지막 값 출력");
ary.forEach(function(item, idx, ary){
	if(idx == 0 || idx == ary.length -1)
	{
		console.log(item);
	}
	if(item % 2 == 0){
		console.log(item);
	}
});



