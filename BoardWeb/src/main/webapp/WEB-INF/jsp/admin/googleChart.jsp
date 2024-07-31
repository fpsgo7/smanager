<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- googleChart.jsp -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
  google.charts.load('current', {'packages':['corechart']});
  let dataAry = [];
  dataAry.push(['작성자','작성건수']);
  
  let url = 'countByMember.do';
  fetch(url)// 현제 기본값 method:'get' , headers: '', body:''
  	.then(function(result){
		return result.json(); // 문자열 -> 객체
	})
  	.then(function(result){ 
		console.log(result);// 위의 then 의 return 을 result 에 받는다.
		result.forEach(member => {
			dataAry.push([member.member_name,member.member_cnt]);
		});
		google.charts.setOnLoadCallback(drawChart);
  	});
  
  function drawChart() {

    var data = google.visualization.arrayToDataTable(dataAry);

    var options = {
      title: '작성자별 작성 건수',
   	  //is3D: true,// 3d 옵션
   	  pieHole: 0.4, // 가운데 구멍 3d 옵션과 결합할 수 없다.
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data, options);
  }

</script>
<h3>차트....</h3>
<div id="piechart" style="width: 900px; height: 500px;"></div>
