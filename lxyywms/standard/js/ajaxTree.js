 //定义XMLHttpRequest对象
 var http_request=false;
 var node="";
 function send_request(url,date,processRequest){
 	http_request=false;
 	//开始初始化XMLHttpRequest对象
 	if(window.XMLHttpRequest){//Mozilla等浏览器初始化XMLHttpRequest过程
 		http_request=new XMLHttpRequest();
 		//有些版本的Mozilla浏览器处理服务器返回的未包含XML mime-type头部信息的内容时会出错.
 		//因此,要确保返回的内容包含text/xml信息.
 		if(http_request.overrideMimeType){
 			http_request.overrideMimeType("text/xml");
 		}
 	}
 	else if(window.ActiveXObject){//IE浏览器初始化XMLHttpRequest过程
 		try{
 			http_request=new ActiveXObject("Msxml2.XMLHTTP");
 		}
 		catch(e){
 			try{
 				http_request=new ActiveXObject("Microsoft.XMLHTTP");
 			}
 			catch(e){}
 		}
 	}
 	//异常,创建对象失败
 	if(!http_request){
 		window.alert("不能创建XMLHttpRequest对象实例!");
 		return false;
 	}
 	//指定响应处理函数
 	http_request.onreadystatechange=processRequest;
 	//发送HTTP请求信息
 	http_request.open("GET",url,true);
 	http_request.send(date);
 }

 //处理返回信息函数
 function processTree(){
 	//判断对象状态
 	if(http_request.readyState==4){
 		//判断HTTP状态码
 		if(http_request.status==200){
 			//信息已经成功返回,将返回信息输出到本页面
			//alert(http_request.responseText);
			//alert(node);
			var obj=document.getElementById(node);
 			obj.innerHTML=http_request.responseText;
 		}
 		else {
 			//请求页面有问题
 			alert("树加载异常:"+http_request.status);
 		}
 	}
 }
 /**
  * 单击树节点
  * @param node_id 单击节点的ID
  * @param level 单击节点的级别
  * @param styleClass 样式
  * @param imageURL 图片目录
  * @param imgClass 图片样式
  * @param lineImg 直线图片
  * @param endLineImg 结尾线图片
  * @param fileImg 文件图片
  * @param sign_img_open 展开节点图片
  * @param sign_img_close 关闭节点图片
  * @param node_img_open 节点展开时图片
  * @param node_img_close 节点关闭时图片
  */
 function clickTreeNode(node_id,level,styleClass,imageURL,imgClass,lineImg,endLineImg,fileImg,sign_img_open,sign_img_close,node_img_open,node_img_close){
	//alert(node_id+":"+level);
	var state=document.getElementById("tree_"+level+"_"+node_id).style.display;
	node="tree_"+level+"_"+node_id;
        var line="";
	for(var i=0;i<level;i++){
		line=line+"<img align=\"absmiddle\" class=\""+imgClass+"\" src=\""+imageURL+lineImg+"\">";
	}
	if(state=="none"){
		document.getElementById(node).innerHTML="<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\""+styleClass+"\"><tr><td>"+line+"<img align=\"absmiddle\" class=\""+imgClass+"\" src=\""+imageURL+endLineImg+"\"><img align=\"absmiddle\" class=\""+imgClass+"\" src=\""+imageURL+fileImg+"\">加载中...</td></tr></table>";
		document.getElementById(node).style.display="";
		document.getElementById("sign_img_"+level+"_"+node_id).src=imageURL+sign_img_open;
		document.getElementById("node_img_"+level+"_"+node_id).src=imageURL+node_img_open;
		
		//获取工程名称
		var tmpHPage = document.URL.split( "/" );  
		var projectName = tmpHPage[3];

		send_request("/"+projectName+"/treenodes?node="+node_id+"&level="+level,null,processTree);
	}else{
		document.getElementById(node).style.display="none";
		document.getElementById("sign_img_"+level+"_"+node_id).src=imageURL+sign_img_close;
		document.getElementById("node_img_"+level+"_"+node_id).src=imageURL+node_img_close;
	}
	
	document.getElementById("hiddenId").value = node_id;  //设置隐藏id
	document.getElementById("hiddenLevel").value = level; //设置隐藏level
 }
 
 function setIdAndLevel(node_id, name, level){
 	document.getElementById("hiddenId").value = node_id;  //设置隐藏id
 	document.getElementById("hiddenName").value = name;   //设置隐藏name
	document.getElementById("hiddenLevel").value = level; //设置隐藏level
 
 }