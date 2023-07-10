var target_time_per_day = 4;
var start_list = document.getElementsByClassName("startList").item(0).getElementsByTagName("td");
var end_list = document.getElementsByClassName("endList").item(0).getElementsByTagName("td");
var time_to_wear = document.getElementsByClassName("timeToWear").item(0).getElementsByTagName("td");
target_time_per_day = Number (time_to_wear.item(0).innerHTML)
calendarInit();


/*
    달력 렌더링 할 때 필요한 정보 목록
    현재 월(초기값 : 현재 시간)
    금월 마지막일 날짜와 요일
    전월 마지막일 날짜와 요일
*/

function calendarInit() {

    // 날짜 정보 가져오기
    const date = new Date(); // 현재 날짜(로컬 기준)
    const utc = date.getTime() + (date.getTimezoneOffset() * 60 * 1000); // utc 표준시 도출
    const kstGap = 9 * 60 * 60 * 1000; // 한국 kst 기준시간 더하기
    const today = new Date(utc + kstGap); // 한국 시간으로 date 객체 만들기(오늘)

    let thisMonth = new Date(today.getFullYear(), today.getMonth(), today.getDate());
    // 달력에서 표기하는 날짜 객체

    currentYear = thisMonth.getFullYear();
    currentMonth = thisMonth.getMonth();
    currentDate = thisMonth.getDate();

    // 캘린더 렌더링
    renderCalender(thisMonth);
    // timeline graph

    function renderCalender(thisMonth) {

        currentYear = thisMonth.getFullYear();
        currentMonth = thisMonth.getMonth();
        currentDate = thisMonth.getDate();

        // 이전 달의 마지막 날 날짜와 요일 구하기
        const startDay = new Date(currentYear, currentMonth, 0);
        const prevDate = startDay.getDate();
        const prevDay = startDay.getDay();

        // 이번 달의 마지막날 날짜와 요일 구하기
        const endDay = new Date(currentYear, currentMonth + 1, 0);
        const nextDate = endDay.getDate();
        const nextDay = endDay.getDay();

        // 현재 년도, 월 출력
        document.getElementsByClassName('year-month')[0].innerText = currentYear + '.' + (currentMonth + 1);

        const calendar = document.querySelector('.dates');
        calendar.innerHTML = '';

        // 지난달
        for (let i = prevDate - prevDay; i <= prevDate; i++) {
            calendar.innerHTML = calendar.innerHTML + '<div class="day prev disable" data-date=' + i + '>' + i + '</div>'
        }
        // 이번달
        listIndex = find_month_index(start_list, currentYear, currentMonth + 1);
        for (let i = 1; i <= nextDate; i++) {
            calendar.innerHTML = calendar.innerHTML + '<div class="day current" ' +
                'data-date=' + currentYear + '.' + (currentMonth + 1) + '.' + i + '>' + i + progressBar(currentYear, currentMonth + 1, i) + '</div>'
        }

        // 다음달
        for (let i = 1; i <= (7 - nextDay == 1 ? 0 : 6 - nextDay); i++) {
            calendar.innerHTML = calendar.innerHTML + '<div class="day next disable" data-date=' + i + '>' + i + '</div>'
        }

        // 오늘 날짜 표시
        if (today.getMonth() == currentMonth) {
            let selectDate = today.getDate();
            let currentMonthDate = document.querySelectorAll('.dates .current');
            currentMonthDate[selectDate - 1].classList.add('select_day');
        }

    }

    // move prev month
    document.getElementsByClassName('move-prev')[0].addEventListener('click', function () {
        thisMonth = new Date(currentYear, currentMonth - 1, 1);
        renderCalender(thisMonth);
        drawChart();
    });

    // move next month
    document.getElementsByClassName('move-next')[0].addEventListener('click', function () {
        thisMonth = new Date(currentYear, currentMonth + 1, 1);
        renderCalender(thisMonth);
        drawChart();
    });

    // click date
    document.querySelectorAll('.dates').forEach((i) => {
        i.addEventListener('click', function (event) {
            let selectDay = event.target.getAttribute('data-date');
            let selectDate = selectDay.split('.').reverse()[0]; // 선택한 날짜
            let currentMonthDate = document.querySelectorAll('.dates .current');
            currentMonthDate.forEach((i) => {
                i.classList.remove('select_day'); // 이전 선택 날짜 삭제
            });
            currentMonthDate[selectDate - 1].classList.add('select_day'); // 현재 선택 날짜
            let temp = document.querySelector('#example').children.item(0).children.item(0).children.item(0).children.item(1);
            temp.scrollTo({top: 41 * (selectDate - 1)});
        });
    });



}

function find_month_index(list, y, m) {
    for (let index = 0; index < list.length; index++){
        let year = subYear(list.item(index).innerHTML) ;
        let month = subMonth(list.item(index).innerHTML);
        if (y == year && m == month){
            return index;
        }
    }
    return -1;
}

function subYear(strTime) {
    return Number(strTime.substring(0, 4));
}
function subMonth(strTime) {
    return Number(strTime.substring(4, 6));
}
function subDate(strTime) {
    return Number(strTime.substring(6, 8));
}
function subHour(strTime) {
    return Number(strTime.substring(8, 10));
}

function checkIndex(index) {
    if (index == -1 || index >= start_list.length) return false;
    return true;
}

function checkDate(y, m, d, index) {
    if (y != subYear(start_list.item(index).innerHTML) || m != subMonth(start_list.item(index).innerHTML) || d != subDate(start_list.item(index).innerHTML)) return false;
    return true;
}


function progressBar(currY, currM, currDate) {
    if (!checkIndex(listIndex) || !checkDate(currY, currM, currDate, listIndex)) return '';
    let color_code;
    let textColor = "color:";
    let backgroundColor = "background: ";
    let w = 0; // w = 넘치는 요소 제한
    let values = [];
    let total = Math.round(calculate_hour(start_list, end_list, currDate) / target_time_per_day * 100);

    if (total <= 25) color_code = "#FF7E7E";
    else if (total > 25 && total < 80) color_code = "#2EE47A";
//    else if (value > 50 && value < 80) color_code = "deepskyblue";
    else if (total >= 80 && total <= 100) color_code = "#5F7FFB";
    else color_code = "#00219B";

    textColor += color_code;
    backgroundColor += color_code;

    let str = '<span class="percent-text" style=' + textColor + '>' + total + '%</span>' +
        '<div class="progress-container">';
    for (let j = 0; j < values.length; j++) {
        let t = Math.round(values[j] / target_time_per_day * 100);
        w += t;
        if (w > 100) // progress + split 요소 너비의 합이 100% 이상이 되면
            t -= w - 100;  // 넘치는 부분 자르기
        let width = "width:" + t + "%;";
        str += '<div class="progress-bar" style="'+  width + backgroundColor +'">&nbsp;</div>'
        if (j < values.length - 1) {
            str += '<div class="split-bar">&nbsp;</div>';
            w += 5; // split-bar 비율
        }
    }
    str += '</div>';
    return str;

    function calculate_hour(start, end, currDate){
        let sumHour = 0;
        for (let j = listIndex; j < start.length && checkDate(currY, currM, currDate, j); j++) {
            let h = subHour(end.item(j).innerHTML) - subHour(start.item(j).innerHTML);
            values.push(h);
            sumHour += h;
            listIndex++;
        }
        return sumHour;
    }
}

/* daily time graph sample */

var scriptElement = document.createElement("script");
scriptElement.src = "https://www.gstatic.com/charts/loader.js";
scriptElement.defer = true;

scriptElement.onload = function () {
    google.charts.load("current", {packages: ["timeline"]});
    google.charts.setOnLoadCallback(drawChart);
};
document.head.appendChild(scriptElement);

google.charts.load("current", {packages: ["timeline"]});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {

    var container = document.getElementById('example');
    var chart = new google.visualization.Timeline(container);
    var dataTable = new google.visualization.DataTable();

    const date = new Date(currentYear, currentMonth + 1, 0).getDate(); // 현재 Month 마지막 날짜
    let index = find_month_index(start_list, currentYear, currentMonth + 1);
    dataTable.addColumn({type: 'string', id: 'date'});
    dataTable.addColumn({type: 'date', id: 'Start'});
    dataTable.addColumn({type: 'date', id: 'End'});
    for (let i = 1; i <= date; i++){
        let startHour = 0;
        let endHour = 0;
        if (checkIndex(index) && checkDate(currentYear, currentMonth + 1, i, index)) {
            startHour = subHour(start_list.item(index).innerHTML);
            endHour = subHour(end_list.item(index).innerHTML);
            index++;

        }
        dataTable.addRows([[i+'일', new Date(0,0,0,startHour,0,0), new Date(0,0,0,endHour,0,0)]]);
        if (checkIndex(index) && checkDate(currentYear, currentMonth + 1, i, index)) i--;
        if (i == date) dataTable.addRows([[i+'일', new Date(0,0,0,23,0,0), new Date(0,0,0,23,0,0)]]);
    }

    var options = {
        timeline: { singleColor: '#5F7FFB' },
        height: 360,
        hAxis: {
            minValue: new Date(0, 0, 0, 1),
            maxValue: new Date(0, 0, 0, 23),
            format: 'H',
        }
    };
    chart.draw(dataTable, options);
}