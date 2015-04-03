 //����XMLHttpRequest����
 var http_request=false;
 var node="";
 function send_request(url,date,processRequest){
 	http_request=false;
 	//��ʼ��ʼ��XMLHttpRequest����
 	if(window.XMLHttpRequest){//Mozilla���������ʼ��XMLHttpRequest����
 		http_request=new XMLHttpRequest();
 		//��Щ�汾��Mozilla�����������������ص�δ����XML mime-typeͷ����Ϣ������ʱ�����.
 		//���,Ҫȷ�����ص����ݰ���text/xml��Ϣ.
 		if(http_request.overrideMimeType){
 			http_request.overrideMimeType("text/xml");
 		}
 	}
 	else if(window.ActiveXObject){//IE�������ʼ��XMLHttpRequest����
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
 	//�쳣,��������ʧ��
 	if(!http_request){
 		window.alert("���ܴ���XMLHttpRequest����ʵ��!");
 		return false;
 	}
 	//ָ����Ӧ������
 	http_request.onreadystatechange=processRequest;
 	//����HTTP������Ϣ
 	http_request.open("GET",url,true);
 	http_request.send(date);
 }

 //��������Ϣ����
 function processTree(){
 	//�ж϶���״̬
 	if(http_request.readyState==4){
 		//�ж�HTTP״̬��
 		if(http_request.status==200){
 			//��Ϣ�Ѿ��ɹ�����,��������Ϣ�������ҳ��
			//alert(http_request.responseText);
			//alert(node);
			var obj=document.getElementById(node);
 			obj.innerHTML=http_request.responseText;
 		}
 		else {
 			//����ҳ��������
 			alert("�������쳣:"+http_request.status);
 		}
 	}
 }
 /**
  * �������ڵ�
  * @param node_id �����ڵ��ID
  * @param level �����ڵ�ļ���
  * @param styleClass ��ʽ
  * @param imageURL ͼƬĿ¼
  * @param imgClass ͼƬ��ʽ
  * @param lineImg ֱ��ͼƬ
  * @param endLineImg ��β��ͼƬ
  * @param fileImg �ļ�ͼƬ
  * @param sign_img_open չ���ڵ�ͼƬ
  * @param sign_img_close �رսڵ�ͼƬ
  * @param node_img_open �ڵ�չ��ʱͼƬ
  * @param node_img_close �ڵ�ر�ʱͼƬ
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
		document.getElementById(node).innerHTML="<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\""+styleClass+"\"><tr><td>"+line+"<img align=\"absmiddle\" class=\""+imgClass+"\" src=\""+imageURL+endLineImg+"\"><img align=\"absmiddle\" class=\""+imgClass+"\" src=\""+imageURL+fileImg+"\">������...</td></tr></table>";
		document.getElementById(node).style.display="";
		document.getElementById("sign_img_"+level+"_"+node_id).src=imageURL+sign_img_open;
		document.getElementById("node_img_"+level+"_"+node_id).src=imageURL+node_img_open;
		
		//��ȡ��������
		var tmpHPage = document.URL.split( "/" );  
		var projectName = tmpHPage[3];

		send_request("/"+projectName+"/treenodes?node="+node_id+"&level="+level,null,processTree);
	}else{
		document.getElementById(node).style.display="none";
		document.getElementById("sign_img_"+level+"_"+node_id).src=imageURL+sign_img_close;
		document.getElementById("node_img_"+level+"_"+node_id).src=imageURL+node_img_close;
	}
	
	document.getElementById("hiddenId").value = node_id;  //��������id
	document.getElementById("hiddenLevel").value = level; //��������level
 }
 
 function setIdAndLevel(node_id, name, level){
 	document.getElementById("hiddenId").value = node_id;  //��������id
 	document.getElementById("hiddenName").value = name;   //��������name
	document.getElementById("hiddenLevel").value = level; //��������level
 
 }