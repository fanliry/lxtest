//����϶��ı���ĵ�Ԫ����    ���ID��theObjTable
  function MouseDownToResize(obj)
  {   
	  obj.mouseDownX=event.clientX;   
	  obj.pareneTdW=obj.parentElement.offsetWidth;   
	  obj.pareneTableW=theObjTable.offsetWidth;   
	  obj.setCapture();   
  }   
  function MouseMoveToResize(obj)
  {   
          if(!obj.mouseDownX)   return   false;   
          var   newWidth=obj.pareneTdW*1+event.clientX*1-obj.mouseDownX;   
          if(newWidth>0)   
          {   
		  	obj.parentElement.style.width   =   newWidth;   
		  	theObjTable.style.width=obj.pareneTableW*1+event.clientX*1-obj.mouseDownX;   
		  }   
  }   
  function MouseUpToResize(obj)
  {   
  	obj.releaseCapture();   
  	obj.mouseDownX=0;   
  } 