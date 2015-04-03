	/********??????start?**********/
	var t_id = null;
	var pos = 0;
	var dir = 2;
	var len = 0;
	function Animate() {
		if (pos == 0) len += dir;
		if (len > 32 || pos > 79) pos += dir;
		if (pos > 79) len -= dir;
		if (pos > 79 && len == 0) pos = 0;
		document.getElementById("progress").style.left = pos;
		document.getElementById("progress").style.width = len;
	}
	function RemoveLoading() {
		if(t_id != null){
			this.clearInterval(t_id);
			document.getElementById("loader_container").style.display = "none";
		}
	}
	function Loading(){
		pos = 0;
		dir = 2;
		len = 0;
		document.getElementById("loader_container").style.display = "block";
		t_id = setInterval(Animate,20);
	}
	/********??????end?**********/