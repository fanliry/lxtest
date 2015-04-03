var cal;
var isFocus=false; //�Ƿ�Ϊ����
function calendartime(obj)
{
	SetDate(obj,'yyyy-MM-dd hh:mm:ss');
}
function SetDate(obj,strFormat)
{
    var date = new Date();
    var by = date.getFullYear()-10; //��Сֵ �� 10 ��ǰ
    var ey = date.getFullYear()+10; //���ֵ �� 10 ���
    //��ʼ��Ϊ���İ棬1ΪӢ�İ�
    cal = (cal==null) ? new Calendar(by, ey, 0,strFormat) : (cal.dateFormatStyle == strFormat ? cal : new Calendar(by, ey, 0,strFormat));
    cal.show(obj);
}
/**//* �������� */
String.prototype.toDate = function(style){
var y = this.substring(style.indexOf('y'),style.lastIndexOf('y')+1);//��
var m = this.substring(style.indexOf('M'),style.lastIndexOf('M')+1);//��
var d = this.substring(style.indexOf('d'),style.lastIndexOf('d')+1);//��
var h = this.substring(style.indexOf('h'),style.lastIndexOf('h')+1);//ʱ
var i = this.substring(style.indexOf('m'),style.lastIndexOf('m')+1);//��
var s = this.substring(style.indexOf('s'),style.lastIndexOf('s')+1);//��
if(isNaN(y)) y = new Date().getFullYear();
if(isNaN(m)) m = new Date().getMonth();
if(isNaN(d)) d = new Date().getDate();
if(isNaN(h)) h = new Date().getHours();
if(isNaN(i)) i = new Date().getMinutes();
if(isNaN(s)) s = new Date().getSeconds();
var dt ;
eval ("dt = new Date('"+ y+"', '"+(m-1)+"','"+ d +"','"+ h +"','"+ i+"','"+ s +"')");
return dt;
}
/**//* ��ʽ������ */
Date.prototype.format = function(style){
var o = {
    "M+" : this.getMonth() + 1, //month
    "d+" : this.getDate(),      //day
    "h+" : this.getHours(),     //hour
    "m+" : this.getMinutes(),   //minute
    "s+" : this.getSeconds(),   //second
    "w+" : "��һ����������".charAt(this.getDay()),   //week
    "q+" : Math.floor((this.getMonth() + 3) / 3), //quarter
    "S" : this.getMilliseconds() //millisecond
}
if(/(y+)/.test(style)){
    style = style.replace(RegExp.$1,
    (this.getFullYear() + "").substr(4 - RegExp.$1.length));
}
for(var k in o){
    if(new RegExp("("+ k +")").test(style)){
      style = style.replace(RegExp.$1,
        RegExp.$1.length == 1 ? o[k] :
        ("00" + o[k]).substr(("" + o[k]).length));
    }
}
return style;
};

/**//*
* ������
* @param   beginYear 1990
* @param   endYear   2010
* @param   lang      0(����)|1(Ӣ��) ����������
* @param   dateFormatStyle "yyyy-MM-dd";
*/
function Calendar(beginYear, endYear, lang, dateFormatStyle){
this.beginYear = 1990;
this.endYear = 2010;
this.lang = 0;            //0(����) | 1(Ӣ��)
this.dateFormatStyle = "yyyy-MM-dd";

if (beginYear != null && endYear != null){
    this.beginYear = beginYear;
    this.endYear = endYear;
}
if (lang != null){
    this.lang = lang
}

if (dateFormatStyle != null){
    this.dateFormatStyle = dateFormatStyle
}

this.dateControl = null;
this.panel = this.getElementById("calendarPanel");
this.container = this.getElementById("ContainerPanel");
this.form = null;

this.date = new Date();
this.year = this.date.getFullYear();
this.month = this.date.getMonth();


this.colors = {
"cur_word"      : "#FFFFFF", //��������������ɫ
"cur_bg"        : "#95B7F3", //�������ڵ�Ԫ��Ӱɫ
"sel_bg"        : "#0000FF", //�ѱ�ѡ������ڵ�Ԫ��Ӱɫ
"sun_word"      : "#FF0000", //������������ɫ
"sat_word"      : "#0000FF", //������������ɫ
"td_word_light" : "#000080", //��Ԫ��������ɫ
"td_word_dark"  : "#DCDCDC",  //��Ԫ�����ְ�ɫ
"td_bg_out"     : "#F5F5FA", //��Ԫ��Ӱɫ
"td_bg_over"    : "#95B7F3", //��Ԫ��Ӱɫ
"tr_word"       : "#FFFFFF", //����ͷ������ɫ
"tr_bg"         : "#95B7F3", //����ͷ��Ӱɫ
"input_border"  : "#95B7F3", //input�ؼ��ı߿���ɫ
"input_bg"      : "#F5F5FA",  //input�ؼ��ı�Ӱɫ
"input_bt"      : "#E6E6FA",  //��ť�ؼ��ı�Ӱɫ
"tb_border"  	: "#95B7F3"  //�߿���ɫ
}

this.draw();
this.bindYear();
this.bindMonth();
this.changeSelect();
this.bindData();
}

/**//*
* ���������ԣ����԰�����������չ��
*/
Calendar.language ={
"year"   : [[""], [""]],
"months" : [["һ��","����","����","����","����","����","����","����","����","ʮ��","ʮһ��","ʮ����"],
        ["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"]
         ],
"weeks" : [["��","һ","��","��","��","��","��"],
        ["SUN","MON","TUR","WED","THU","FRI","SAT"]
         ],
"abort" : [["ʱ��"], ["TIME"]],
"clear" : [["���"], ["CLS"]],
"today" : [["����"], ["TODAY"]],
"close" : [["�ر�"], ["CLOSE"]]
}

Calendar.prototype.draw = function(){
calendart = this;

var mvAry = [];
mvAry[mvAry.length] = ' <div name="calendarForm" style="margin: 0px; border: 2px ' + calendart.colors["tb_border"] + ' solid;" >';
mvAry[mvAry.length] = '    <table border="0" cellpadding="0" cellspacing="0" >';
mvAry[mvAry.length] = '      <tr>';
mvAry[mvAry.length] = '        <th align="left" width="1%"><input style="border: 1px solid ' + calendart.colors["input_border"] + ';background-color:' + calendart.colors["input_bg"] + ';width:16px;height:20px;" name="prevMonth" type="button" id="prevMonth" value="<" /></th>';
mvAry[mvAry.length] = '        <th align="center" width="98%" nowrap="nowrap" style="background-color:' + calendart.colors["input_bg"] + ';"><select name="calendarYear" id="calendarYear" style="font-weight:normal;font-size:12px;font-family:����;"></select><select name="calendarMonth" id="calendarMonth" style="font-weight:normal;font-size:12px;font-family:����;"></select></th>';
mvAry[mvAry.length] = '        <th align="right" width="1%"><input style="border: 1px solid ' + calendart.colors["input_border"] + ';background-color:' + calendart.colors["input_bg"] + ';width:16px;height:20px;" name="nextMonth" type="button" id="nextMonth" value=">" /></th>';
mvAry[mvAry.length] = '      </tr>';
mvAry[mvAry.length] = '    </table>';
mvAry[mvAry.length] = '    <table id="calendarTable" width="100%" style="border:0px solid #AAAAAA;background-color:#F5F5FA;font-weight:normal;font-size:12px;font-family:����;" border="0" cellpadding="2" cellspacing="0">';
mvAry[mvAry.length] = '      <tr>';
for(var i = 0; i < 7; i++){
    mvAry[mvAry.length] = '      <th style="background-color:' + calendart.colors["tr_bg"] + ';color:' + calendart.colors["tr_word"] + ';">' + Calendar.language["weeks"][this.lang][i] + '</th>';
}
mvAry[mvAry.length] = '      </tr>';
for(var i = 0; i < 6;i++){
    mvAry[mvAry.length] = '    <tr align="center">';
    for(var j = 0; j < 7; j++){
      if (j == 0){
        mvAry[mvAry.length] = ' <td style="cursor:default;color:' + calendart.colors["sun_word"] + ';"></td>';
      } else if(j == 6){
        mvAry[mvAry.length] = ' <td style="cursor:default;color:' + calendart.colors["sat_word"] + ';"></td>';
      } else{
        mvAry[mvAry.length] = ' <td style="cursor:default;"></td>';
      }
    }
    mvAry[mvAry.length] = '    </tr>';
}

mvAry[mvAry.length] = '      <tr align="center" style="font-size:12px;">';
mvAry[mvAry.length] = '        <td name="abort" id="abort" colspan="1" style="cursor:default;">' + Calendar.language["abort"][this.lang] + '</td>';
mvAry[mvAry.length] = '        <td colspan="6"><select name="calendarHour" id="calendarHour"></select>';
mvAry[mvAry.length] = ':<select name="calendarMinute" id="calendarMinute"></select>';
mvAry[mvAry.length] = ':<select name="calendarSecond" id="calendarSecond"></select>';
mvAry[mvAry.length] = '      </td></tr>';

mvAry[mvAry.length] = '      <tr style="background-color:' + calendart.colors["input_bg"] + ';">';
mvAry[mvAry.length] = '        <th colspan="2"><input name="calendarClear" type="button" id="calendarClear" value="' + Calendar.language["clear"][this.lang] + '" style="border: 1px solid ' + calendart.colors["input_border"] + ';background-color:' + calendart.colors["input_bt"] + ';width:100%;height:20px;font-family:����;font-size:12px;"/></th>';
mvAry[mvAry.length] = '        <th colspan="3"><input name="calendarToday" type="button" id="calendarToday" value="' + Calendar.language["today"][this.lang] + '" style="border: 1px solid ' + calendart.colors["input_border"] + ';background-color:' + calendart.colors["input_bt"] + ';width:100%;height:20px;font-family:����;font-size:12px;"/></th>';
mvAry[mvAry.length] = '        <th colspan="2"><input name="calendarClose" type="button" id="calendarClose" value="' + Calendar.language["close"][this.lang] + '" style="border: 1px solid ' + calendart.colors["input_border"] + ';background-color:' + calendart.colors["input_bt"] + ';width:100%;height:20px;font-family:����;font-size:12px;"/></th>';
mvAry[mvAry.length] = '      </tr>';
mvAry[mvAry.length] = '    </table>';
mvAry[mvAry.length] = ' </div>';
this.panel.innerHTML = mvAry.join("");

var obj = this.getElementById("prevMonth");
obj.onclick = function (){calendart.goPrevMonth(calendart);}
obj.onblur = function (){calendart.onblur();}
this.prevMonth= obj;

obj = this.getElementById("nextMonth");
obj.onclick = function (){calendart.goNextMonth(calendart);}
obj.onblur = function (){calendart.onblur();}
this.nextMonth= obj;


obj = this.getElementById("calendarClear");
obj.onclick = function (){calendart.dateControl.value = "";calendart.hide();}
this.calendarClear = obj;

obj = this.getElementById("calendarClose");
obj.onclick = function (){calendart.hide();}
this.calendarClose = obj;

obj = this.getElementById("calendarYear");
obj.onchange = function (){calendart.update(calendart);}
obj.onblur = function (){calendart.onblur();}
this.calendarYear = obj;

obj = this.getElementById("calendarMonth");
with(obj)
{
    onchange = function (){calendart.update(calendart);}
    onblur = function (){calendart.onblur();}
}this.calendarMonth = obj;

obj = this.getElementById("calendarHour");
with(obj)
{
    length = 0;
    for (var i = 0; i < 24; i++){
        if(i<10){options[length] = new Option("0"+i,"0"+i);}
        else{options[length] = new Option(i,i);}
    }
}this.calendarHour = obj;

obj = this.getElementById("calendarMinute");
with(obj)
{
    length = 0;
    for (var i = 0; i < 60; i++){
        if(i<10){options[length] = new Option("0"+i,"0"+i);}
        else{options[length] = new Option(i,i);}
    }
}this.calendarMinute = obj;

obj = this.getElementById("calendarSecond");
with(obj)
{
    length = 0;
    for (var i = 0; i < 60; i++){
        if(i<10){options[length] = new Option("0"+i,"0"+i);}
        else{options[length] = new Option(i,i);}
    }
}this.calendarSecond = obj;

obj = this.getElementById("calendarToday");
obj.onclick = function (){
    var today = new Date();
    calendart.date = today;
    calendart.year = today.getFullYear();
    calendart.month = today.getMonth();
    calendart.changeSelect();
    calendart.bindData();
    calendart.dateControl.value = today.format(calendart.dateFormatStyle);
    calendart.hide();
}
this.calendarToday = obj;
}

//��������������
Calendar.prototype.bindYear = function(){
var cy = this.calendarYear;
cy.length = 0;
for (var i = this.beginYear; i <= this.endYear; i++){
    cy.options[cy.length] = new Option(i + Calendar.language["year"][this.lang], i);
}
}

//�·������������
Calendar.prototype.bindMonth = function(){
var cm = this.calendarMonth;
cm.length = 0;
for (var i = 0; i < 12; i++){
    cm.options[cm.length] = new Option(Calendar.language["months"][this.lang][i], i);
}
}

//��ȡСʱ������
Calendar.prototype.getHour = function(){
return this.calendarHour.options[this.calendarHour.selectedIndex].value;
}

//��ȡ���ӵ�����
Calendar.prototype.getMinute = function(){
return this.calendarMinute.options[this.calendarMinute.selectedIndex].value;
}

//��ȡ�������
Calendar.prototype.getSecond = function(){
return this.calendarSecond.options[this.calendarSecond.selectedIndex].value;
}

//��ǰһ��
Calendar.prototype.goPrevMonth = function(e){
if (this.year == this.beginYear && this.month == 0){return;}
this.month--;
if (this.month == -1){
    this.year--;
    this.month = 11;
}
this.date = new Date(this.year, this.month, 1,this.getHour(),this.getMinute(),this.getSecond());
this.changeSelect();
this.bindData();
}

//���һ��
Calendar.prototype.goNextMonth = function(e){
if (this.year == this.endYear && this.month == 11){return;}
this.month++;
if (this.month == 12){
    this.year++;
    this.month = 0;
}
this.date = new Date(this.year, this.month, 1,this.getHour(),this.getMinute(),this.getSecond());
this.changeSelect();
this.bindData();
}

//�ı�SELECTѡ��״̬
Calendar.prototype.changeSelect = function(){
var cy = this.calendarYear;
var cm = this.calendarMonth;
var ch = this.calendarHour;
var ci = this.calendarMinute;
var cs = this.calendarSecond;
for (var i= 0; i < cy.length; i++){
    if (cy.options[i].value == this.date.getFullYear()){
      cy[i].selected = true;
      break;
    }
}
for (var i= 0; i < cm.length; i++){
    if (cm.options[i].value == this.date.getMonth()){
      cm[i].selected = true;
      break;
    }
}
for (var i= 0; i < ch.length; i++){
    if (ch.options[i].value == this.date.getHours()){
      ch[i].selected = true;
      break;
    }
}
for (var i= 0; i < ci.length; i++){
    if (ci.options[i].value == this.date.getMinutes()){
      ci[i].selected = true;
      break;
    }
}
for (var i= 0; i < cs.length; i++){
    if (cs.options[i].value == this.date.getSeconds()){
      cs[i].selected = true;
      break;
    }
}
}

//�����ꡢ��
Calendar.prototype.update = function (e){
this.year = e.calendarYear.options[e.calendarYear.selectedIndex].value;
this.month = e.calendarMonth.options[e.calendarMonth.selectedIndex].value;
this.date = new Date(this.year, this.month, 1,this.getHour(),this.getMinute(),this.getSecond());
this.changeSelect();
this.bindData();
}

//�����ݵ�����ͼ
Calendar.prototype.bindData = function (){
var calendart = this;
var dateArray = this.getMonthViewArray(this.date.getFullYear(), this.date.getMonth());
var tds = this.getElementById("calendarTable").getElementsByTagName("td");
for(var i = 0; i < tds.length; i++){
tds[i].style.backgroundColor = calendart.colors["td_bg_out"];
    tds[i].onclick = function () {return;}
    tds[i].onmouseover = function () {return;}
    tds[i].onmouseout = function () {return;}
    if (i > dateArray.length - 1) break;
    tds[i].innerHTML = dateArray[i];
    if (dateArray[i] != " "){
      tds[i].onclick = function () {
        if (calendart.dateControl != null){
          calendart.dateControl.value = new Date(calendart.date.getFullYear(),
                                                calendart.date.getMonth(),
                                                this.innerHTML,
                                                calendart.getHour(),
                                                calendart.getMinute(),
                                                calendart.getSecond()).format(calendart.dateFormatStyle);
        }
        calendart.hide();
      }
      tds[i].onmouseover = function () {
        this.style.backgroundColor = calendart.colors["td_bg_over"];
      }
      tds[i].onmouseout = function () {
        this.style.backgroundColor = calendart.colors["td_bg_out"];
      }
      if (new Date().format("yyyy-MM-dd") ==
          new Date(calendart.date.getFullYear(),
                   calendart.date.getMonth(),
                   dateArray[i]).format("yyyy-MM-dd")) {
        tds[i].style.backgroundColor = calendart.colors["cur_bg"];
        tds[i].onmouseover = function () {
          this.style.backgroundColor = calendart.colors["td_bg_over"];
        }
        tds[i].onmouseout = function () {
          this.style.backgroundColor = calendart.colors["cur_bg"];
        }
      }//end if
      
      //�����ѱ�ѡ������ڵ�Ԫ��Ӱɫ
      if (calendart.dateControl != null && calendart.dateControl.value == new Date(calendart.date.getFullYear(),
                   calendart.date.getMonth(),
                   dateArray[i],
                   calendart.getHour(),
                   calendart.getMinute(),
                   calendart.getSecond()).format(calendart.dateFormatStyle)) {
        tds[i].style.backgroundColor = calendart.colors["sel_bg"];
        tds[i].onmouseover = function () {
          this.style.backgroundColor = calendart.colors["td_bg_over"];
        }
        tds[i].onmouseout = function () {
          this.style.backgroundColor = calendart.colors["sel_bg"];
        }
      }
    }
}
}

//�����ꡢ�µõ�����ͼ����(������ʽ)
Calendar.prototype.getMonthViewArray = function (y, m) {
var mvArray = [];
var dayOfFirstDay = new Date(y, m, 1).getDay();
var daysOfMonth = new Date(y, m + 1, 0).getDate();
for (var i = 0; i < 42; i++) {
    mvArray[i] = " ";
}
for (var i = 0; i < daysOfMonth; i++){
    mvArray[i + dayOfFirstDay] = i + 1;
}
return mvArray;
}

//��չ document.getElementById(id) ������������� from meizz tree source
Calendar.prototype.getElementById = function(id){
if (typeof(id) != "string" || id == "") return null;
if (document.getElementById) return document.getElementById(id);
if (document.all) return document.all(id);
try {return eval(id);} catch(e){ return null;}
}

//��չ object.getElementsByTagName(tagName)
Calendar.prototype.getElementsByTagName = function(object, tagName){
if (document.getElementsByTagName) return document.getElementsByTagName(tagName);
if (document.all) return document.all.tags(tagName);
}

//ȡ��HTML�ؼ�����λ��
Calendar.prototype.getAbsPoint = function (e){
var x = e.offsetLeft;
var y = e.offsetTop;
while(e = e.offsetParent){
    x += e.offsetLeft;
    y += e.offsetTop;
}
return {"x": x, "y": y};
}

//��ʾ����
Calendar.prototype.show = function (dateObj, popControl) {
if (dateObj == null){
    throw new Error("arguments[0] is necessary")
}
this.dateControl = dateObj;

this.date = (dateObj.value.length > 0) ? new Date(dateObj.value.toDate(this.dateFormatStyle)) : new Date() ;//��Ϊ������ʾ��ǰ�·�
this.year = this.date.getFullYear();
this.month = this.date.getMonth();
this.changeSelect();
this.bindData();
if (popControl == null){
    popControl = dateObj;
}
var xy = this.getAbsPoint(popControl);
this.panel.style.left = xy.x -25 + "px";
this.panel.style.top = (xy.y + dateObj.offsetHeight) + "px";

this.panel.style.display = "";
this.container.style.display = "";

dateObj.onblur = function(){calendart.onblur();}
this.container.onmouseover = function(){isFocus=true;}
this.container.onmouseout = function(){isFocus=false;}
}

//��������
Calendar.prototype.hide = function() {
this.panel.style.display = "none";
this.container.style.display = "none";
isFocus=false;
}

//����ת��ʱ��������
Calendar.prototype.onblur = function() {
    if(!isFocus){this.hide();}
}
document.write('<div id="ContainerPanel" style="display:none;"><div id="calendarPanel" style="position: absolute;display: none;z-index: 9999;');
document.write('background-color: #FFFFFF;border: 1px solid #CCCCCC;width:175px;font-size:12px;margin-left:25px;"></div>');
if(document.all)
{
document.write('<iframe style="position:absolute;z-index:2000;width:expression(this.previousSibling.offsetWidth);');
document.write('height:expression(this.previousSibling.offsetHeight);');
document.write('left:expression(this.previousSibling.offsetLeft);top:expression(this.previousSibling.offsetTop);');
document.write('display:expression(this.previousSibling.style.display);" scrolling="no" frameborder="no"></iframe>');
}
document.write('</div>');