var tableSort = function(){
//this.initialize.apply(this,arguments);
}
/*tableSort.prototype = {
initialize : function(tableId,clickRow,startRow,endRow,classUp,classDown,selectClass,firstcolflag){
this.Table = document.getElementById(tableId);
this.rows = this.Table.rows;//������
this.Tags = this.rows[clickRow-1].cells;//��ǩtd
this.up = classUp;
this.down = classDown;
this.startRow = startRow;
this.selectClass = selectClass;
this.firstcolflag = firstcolflag;
this.endRow = (endRow == 999? this.rows.length : endRow);
this.T2Arr = this._td2Array();//������Ӱ���td�Ķ�ά����
this.setShow();
},
//���ñ�ǩ�л�
setShow:function(){
var defaultClass = this.Tags[0].className;
for(var Tag ,i=0;Tag = this.Tags[i];i++){
if(this.firstcolflag && i==0){
	continue;
}
Tag.index = i;
addEventListener(Tag ,'click', Bind(Tag,statu));
}
var _this =this;
var turn = 0;
function statu(){
for(var i=0;i<_this.Tags.length;i++){
_this.Tags[i].className = defaultClass;
}
if(turn==0){
addClass(this,_this.down)
_this.startArray(0,this.index);
turn=1;
}else{
addClass(this,_this.up)
_this.startArray(1,this.index);
turn=0;
}
}
},
//����ѡ������ʽ
colClassSet:function(num,cla){
//�õ���������td
for(var i= (this.startRow-1);i<(this.endRow);i++){
for(var n=0;n<this.rows[i].cells.length;n++){
removeClass(this.rows[i].cells[n],cla);
}
addClass(this.rows[i].cells[num],cla);
}
},
//��ʼ���� num ���ݵڼ������� aord ������˳��
startArray:function(aord,num){
var afterSort = this.sortMethod(this.T2Arr,aord,num);//�����Ķ�ά���鴫�����򷽷���ȥ
this.array2Td(num,afterSort);//���
},
//����Ӱ����к���ת���ɶ�ά����
_td2Array:function(){ 
var arr=[];
for(var i=(this.startRow-1),l=0;i<(this.endRow);i++,l++){
arr[l]=[];
for(var n=0;n<this.rows[i].cells.length;n++){
arr[l].push(this.rows[i].cells[n].innerHTML);
}
}
return arr;
},
//���������Ķ�ά�����������Ӧ���к��е� innerHTML 
array2Td:function(num,arr){
this.colClassSet(num,this.selectClass); 
for(var i= (this.startRow-1),l=0;i<(this.endRow);i++,l++){
for(var n=0;n<this.Tags.length;n++){
this.rows[i].cells[n].innerHTML = arr[l][n]; 
}
}
},
//������һ����ά���飬���ݶ�ά����������е�w�������ٷ��������Ķ�ά����
sortMethod:function(arr,aord,w){
//var effectCol = this.getColByNum(whichCol);
arr.sort(function(a,b){
x = killHTML(a[w]);
y = killHTML(b[w]);
x = x.replace(/,/g,'');
y = y.replace(/,/g,'');
switch (isNaN(x)){
case false:
return Number(x) - Number(y);
break;
case true:
return x.localeCompare(y);
break;
}
});
arr = aord==0?arr:arr.reverse();
return arr;
}
}*/
/*-----------------------------------*/
function addEventListener(o,type,fn){
if(o.attachEvent){o.attachEvent('on'+type,fn)}
else if(o.addEventListener){o.addEventListener(type,fn,false)}
else{o['on'+type] = fn;}
}

function hasClass(element, className) { 
var reg = new RegExp('(\\s|^)'+className+'(\\s|$)'); 
return element.className.match(reg); 
} 

function addClass(element, className) { 
if (!this.hasClass(element, className)) 
{ 
element.className += " "+className; 
} 
} 

function removeClass(element, className) { 
if (hasClass(element, className)) { 
var reg = new RegExp('(\\s|^)'+className+'(\\s|$)'); 
element.className = element.className.replace(reg,' '); 
} 
} 

var Bind = function(object, fun) {
return function() {
return fun.apply(object, arguments);
}
}
//ȥ�����е�html���
function killHTML(str){
return str.replace(/<[^>]+>/g,"");
}