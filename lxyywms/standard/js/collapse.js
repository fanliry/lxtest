  function tablecollapse(imageURL)
	{
		/* Variables */
		var collapseClass='footcollapse';
		var collapsePic=imageURL+'ico080426_close.gif';
		var expandPic=imageURL+'ico080426_open.gif';
		var initialCollapse=true;
		// loop through all tables
		var t=document.getElementsByTagName('table');
		var checktest= new RegExp("(^|\\s)" + collapseClass + "(\\s|$)");
		for (var i=0;i<t.length;i++)
		{
			// if the table has not the right class, skip it
			if(!checktest.test(t[i].className)){continue;}
			// make the footer clickable
			t[i].getElementsByTagName('thead')[0].onclick=function()
			{
				// loop through all bodies of this table and show or hide
				// them
				var tb=this.parentNode.getElementsByTagName('tbody');
				for(var i=0;i<tb.length;i++)
				{
					tb[i].style.display=tb[i].style.display=='none'?'':'none';
				}
				// change the image accordingly
				var zx = tb[0];
				if(zx.getElementsByTagName('td') && zx.getElementsByTagName('td').length>0){ //tbody 中有列项
					var li=this.getElementsByTagName('img')[0];
				    li.src=li.src.indexOf(collapsePic)==-1?collapsePic:expandPic;
				}	
			}
			// if the bodies should be collapsed initially, do so
			if(initialCollapse)
			{
				var tb=t[i].getElementsByTagName('tbody');
				for(var j=0;j<tb.length;j++)
				{
				   tb[j].style.display='none';
				}
			}
			// add the image surrounded by a dummy link to allow keyboard
			// access to the last cell in the footer
			var z = t[i].getElementsByTagName('tbody')[0];
			if(z.getElementsByTagName('td') && z.getElementsByTagName('td').length>0){//tbody 中有列项
				var newa=document.createElement('a');
				newa.href='#';
				newa.onclick=function(){return false;}
				var newimg=document.createElement('img');
				newimg.src=initialCollapse?expandPic:collapsePic;
				var tf=t[i].getElementsByTagName('thead')[0];
				var lt=tf.getElementsByTagName('td')[0];
				newa.appendChild(newimg);
				lt.insertBefore(newa,lt.firstChild);
			}	
		}
	}