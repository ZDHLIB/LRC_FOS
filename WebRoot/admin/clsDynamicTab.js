
/****************************************************************** 
 *
 * 动态Tab标签  version 1.0
 *
 ******************************************************************* 
 */
function clsDynamicTab() {
	/*
	 * 窗口列表
	 */
	this.winlist = new Array();
	/*
	 * 最大窗口数 , 目前为 8 
	 */
	this.maxWins = 20;
	/*
	 * 标签宽度
	 */
	this.tagTitleWidth = 137;
	/*
	 * 标签缩进宽度
	 */
	this.indentWidth = 15;
	this.currentwin = null;
	/*
	 * 新建窗口方法
	 */
	this.addwin = addwin;
	/*
	 * 移除窗体
	 */
	this.removewin = removewin;
	/*
	 * 移除所有窗体
	 */
	this.removeall = removeall;
	/*
	 * 激活窗口
	 */
	this.activewin = activewin;
	this.container = container;
	/*
	 * 标题离左边缘的距离
	 */
	this.padLeft = padLeft;
	/*
	 * 标题离右边缘的距离
	 */
	this.padRight = padRight;
	this.scrollWidth = scrollWidth;
	/*
	 * 获取指定 URL 及 Title 在窗口列表中的位置
	 */
	function container(url, title) {
		for (var i = 0; i < this.winlist.length; i++) {
			if (this.winlist[i].title == title && this.winlist[i].url == url) {
				return i;
			}
		}
		return -1;
	}
	/*
	 * 激活窗口
	 */
	function activewin(oEl, refreshFlag) {
		if (oEl == null) {
			this.currentwin = null;
			return;
		}
		var tempzindex = this.currentwin.style.zIndex;
		this.currentwin.wintitle.style.zIndex = this.currentwin.index;
		this.currentwin.style.display = "none";
		this.currentwin.wintitle.style.backgroundImage = "url(images/n02.jpg)";
		oEl.wintitle.style.zIndex = tempzindex;
		oEl.style.display = "";
		oEl.wintitle.style.backgroundImage = "url(images/n01.gif)";
		//oEl.wintitle.style.color = "#ffffff";
		this.currentwin = oEl;
		if (refreshFlag) {//强制刷新
			var obj = oEl.getElementsByTagName("IFRAME")[0].document;
			var tempFlag = getIframeIndex(obj, oEl.getElementsByTagName("IFRAME")[0]);
			if (tempFlag != null) {
				obj.frames[tempFlag].navigate(oEl.getElementsByTagName("IFRAME")[0].src);
			}
		}
		
		//如果不在显示区域内
		var mleft = parseInt(titlelist.style.marginLeft);
		if (isNaN(mleft)) {
			mleft = 0;
		}
		var padleft = this.padLeft(oEl);
		var padright = this.padRight(oEl);
		var clientwidth = titlelist.parentElement.clientWidth;
		if (padleft + mleft > clientwidth) {
			titlelist.style.marginLeft = clientwidth - padleft;
		}
		if (padright < clientwidth && mleft < 0) {
			mleft = clientwidth - this.scrollWidth();
			if (mleft > 0) {
				mleft = 0;
			}
			titlelist.style.marginLeft = mleft;
		}
		if (padleft + mleft < this.tagTitleWidth) {
			titlelist.style.marginLeft = -(padleft - this.tagTitleWidth);
		}
		
	}
	/*
	 * 获取相应IFRAME的Index
	 */
	function getIframeIndex(obj, srcObj) {
		for (var j = 0; j < obj.frames.length; j++) {
			if (obj.frames[j].frameElement == srcObj) {
				return j;
			}
		}
		return null;
	}
	/*
	 * 标题离左边缘的距离
	 */
	function padLeft(oEl) {
		var padleft = oEl.index * this.tagTitleWidth - this.indentWidth * (oEl.index - 1);
		return padleft;
	}
	/*
	 * 标题离右边缘的距离
	 */
	function padRight(oEl) {
		var count = (this.winlist.length - oEl.index) + 1;
		var padright = this.tagTitleWidth * count - this.indentWidth * (count - 1);
		return padright;
	}
	/*
	 * 主要方法 ：动态增加Tab窗口 
	 * 参数     ：打开页面的URL 、 显示Tab的Title
	 */
	function addwin(url, title, refreshFlag) {
		var con = this.container(url, title);
		//是否窗口列表已经包含该页面
		if (con > -1) {
			this.activewin(this.winlist[con], refreshFlag);
			return;
		}
		//判断打开窗口数是否超过了设置的最大数量
		if (this.winlist.length >= this.maxWins) {
			alert("\u8d85\u8fc7\u6700\u5927\u7a97\u53e3\u6570\u9650\u5236\uff08" + this.maxWins + "\uff09\uff0c\u8bf7\u5148\u5173\u95ed\u90e8\u5206\u7a97\u53e3");
			return false;
		}
		oDIV = window.document.createElement("TABLE");
		//往窗口列表内添加窗体对象
		this.winlist[this.winlist.length] = oDIV;
		oDIV.url = url;
		oDIV.title = title;
		oDIV.index = this.winlist.length;
		oDIV.className = "win";
		oDIV.width = "100%";
		oDIV.height = "100%";
		oDIV.cellSpacing = 0;
		oDIV.insertRow().insertCell().innerHTML = "<iframe src='" + url + "' class = 'win1' width='100%' height='100%' frameborder='0'></iframe>";
		var oTitle = window.document.createElement("SPAN");
		oTitle.className = "wintitle";
		/*
		oTitle.ondblclick =function ()
		{
		    win.removewin(win.currentwin);
		};
		*/
		oTitle.style.width = this.tagTitleWidth;
		oTitle.valign = "bottom";
		//oTitle.style.color = "#000000";
		oTitle.style.backgroundImage = "url(images/n01.gif)";
		oTitle.style.left = this.winlist.length == 1 ? 0 : this.winlist[this.winlist.length - 2].wintitle.style.pixelLeft - this.indentWidth;
		oTitle.title = title;
		title = subStr(title, 12);
		oTitle.innerHTML = title == null ? "unkown windows" : title;
		oTitle.win = oDIV;
		oTitle.onclick = new Function("win.activewin(this.win)");
		if (this.currentwin != null) {
			this.currentwin.wintitle.style.backgroundImage = "url(images/n02.jpg)";
			this.currentwin.style.display = "none";
			this.currentwin.wintitle.style.zIndex = this.currentwin.index;
		}
		oDIV.style.zIndex = this.maxWins + 1;
		oTitle.style.zIndex = this.maxWins + 1;
		oDIV.wintitle = oTitle;
		titlelist.insertAdjacentElement("beforeEnd", oTitle);
		var scrollwidth = this.scrollWidth();
		if (scrollwidth > titlelist.parentElement.clientWidth) {
			titlelist.style.marginLeft = titlelist.parentElement.clientWidth - scrollwidth;
		}
		mywindows.insertAdjacentElement("beforeEnd", oDIV);
		this.currentwin = oDIV;
		var iFrameDocument = this.currentwin.getElementsByTagName('IFRAME')[0].contentWindow.document;
		//添加gn_dm ,zn_dm 两个对象，参数为******pfv?gn_dm=1001&zn_dm=101
	//	var url = "/BaseReportController-initReportMain.pfv?bbdm=1241&bbh=1&gn_dm=1002001&zn_dm=10111";
		var gi = url.indexOf("gn_dm");
		var zi = url.indexOf("zn_dm");
	    var gparams, zparams;
    	gparams = url.substring(gi+6, zi-1);  
    	zparams = url.substring(zi+6);
	    var gInput = iFrameDocument.createElement("input");
	    var zInput = iFrameDocument.createElement("input");
	    gInput.type = "hidden"
	    gInput.name = "tab_gn_dm";
		gInput.id = "tab_gn_dm";
	    gInput.value = 	gparams;
	    zInput.type = "hidden"
	    zInput.name = "tab_zn_dm";
		zInput.id = "tab_zn_dm";
	    zInput.value = 	zparams;
	    iFrameDocument.appendChild(gInput);
	    iFrameDocument.appendChild(zInput);
	    
		var oInput = iFrameDocument.createElement("input");
		oInput.style.width = "0px";
		oInput.style.height= "0px";
		iFrameDocument.appendChild(oInput);
		oInput.focus();
		//this.currentwin.getElementsByTagName('IFRAME')[0].contentWindow.document.getElementsByTagName('input')[0].focus();
		
		return oDIV;
	}
	/*
	 * 滚动的距离 
	 */
	function scrollWidth() {
		var n = this.winlist.length;
		var scrollwidth = this.tagTitleWidth * n - this.indentWidth * (n - 1);
		return scrollwidth;
	}
	/*
	 * 移除窗体
	 */
	function removewin(obj) {
		if (obj == null) {
			return;
		}
		var temparr = new Array();
		var afterwin = false;
		for (var i = 0; i < this.winlist.length; i++) {
			if (afterwin) {
				this.winlist[i].wintitle.style.left = this.winlist[i].wintitle.style.pixelLeft + this.indentWidth;
			}
			if (this.winlist[i] != obj) {
				temparr[temparr.length] = this.winlist[i];
			} else {
				afterwin = true;
			}
		}
		this.winlist = temparr;
		if (this.currentwin == obj) {
			this.activewin(this.winlist[this.winlist.length - 1]);
		}
		/*
		if (obj.title=="待办任务" && obj.getElementsByTagName('IFRAME')[0].contentWindow.document.title!="待办任务")
		{
			 
			this.addwin("../../wstasklist.pfv?dealmethod=daiban&zn_dm=A&gn_dm=290","待办任务",true);
		}
		*/
		//alert("3");
		//obj.getElementsByTagName('IFRAME')[0].contentWindow.document.getElementsByTagName('input')[0].focus(); 
		obj.wintitle.removeNode(true);
		obj.removeNode(true);
		//obj.getElementsByTagName('IFRAME')[0].contentWindow.document.focus();
		obj = null;
	}
	/*
	 * 移除所有窗体
	 */
	function removeall() {
		var wincount = this.winlist.length;
		for (var i = wincount - 1; i >= 0; i--) {
			this.removewin(this.winlist[i]);
		}
	}
}
//**************************************************************************
/*
 *  滚动标签
 */
function tabScroll(direction) {
	tabScrollStop();
	direction == "right" ? tabMoveRight() : tabMoveLeft();
}
/*
 *  向右滚动标签
 */
function tabMoveRight() {
	tabMove("right", 100);
	timer = setTimeout(tabMoveRight, 100);
}
/*
 *  向左滚动标签
 */
function tabMoveLeft() {
	tabMove("left", 100);
	timer = setTimeout(tabMoveLeft, 100);
}
/*
 *  停止滚动标签
 */
function tabScrollStop() {
	clearTimeout(timer);
	timer = null;
}
/*
 *  滚动标签
 */
function tabMove(direction, speed) {
	var mleft = parseInt(titlelist.style.marginLeft);
	if (isNaN(mleft)) {
		mleft = 0;
	}
	if (direction == "right") {
		if (titlelist.parentElement.clientWidth >= titlelist.parentElement.scrollWidth) {
			tabScrollStop();
			return;
		} else {
			titlelist.style.marginLeft = mleft - speed;
		}
	} else {
		if (mleft + speed >= 0) {
			titlelist.style.marginLeft = 0;
			tabScrollStop();
			return;
		} else {
			titlelist.style.marginLeft = mleft + speed;
		}
	}
}
//**************************************************************************
var timer = null;
var win = null;
var wins = new Array();
/*
 *  初始化
 */
function init() {
	win = new clsDynamicTab();
}
/*
 *  添加窗体
 */
function AddWin(Url, Title, refreshFlag) {
	wins[wins.length] = win.addwin(Url, Title, refreshFlag);
}
/*
 *  添加窗体(与老版本兼容)
 */
function clsDynamicTab$addTab(Title, Url, refreshFlag) {
	AddWin(Url, Title, refreshFlag);
}
/*
 *  格式化 Title
 */
function subStr(str, len) {
	var strlength = 0;
	var newstr = "";
	for (var i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) >= 1000) {
			strlength += 2;
		} else {
			strlength += 1;
		}
		if (strlength > len) {
			newstr += "...";
			break;
		} else {
			newstr += str.substr(i, 1);
		}
	}
	return newstr;
}
