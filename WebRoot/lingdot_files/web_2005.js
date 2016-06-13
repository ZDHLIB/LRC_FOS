function search(form){
	var SEARCH_KEYWORD;
	SEARCH_KEYWORD = form.search_key.value;
	if(form.search_key.value == ""){
		alert("不可是空白");
		form.search_key.focus();
		return false;			
		}		
	if((SEARCH_KEYWORD.indexOf("\'") != -1)||(SEARCH_KEYWORD.indexOf("$") != -1)||(SEARCH_KEYWORD.indexOf(";") != -1)||(SEARCH_KEYWORD.indexOf('"') != -1)||(SEARCH_KEYWORD.indexOf("!'") != -1)){
		alert("資料處理錯誤!!");
		form.search_key.focus();
		return false;			
		}	
	return true;
	}

function mcenter2(){
	var ww = 700;
	var wh = 250;
	var huw = (screen.width - ww) / 2;
	var hsh = (screen.height - wh) /2;
	window.moveTo(huw,hsh);
	return true;
	}

function bulletin(openFilename,windowName,properties) {
  window.open(openFilename,windowName,properties);
  }
  
var menu_num = 4;
var hideLayerMethod = "";

function show_menu(menu_NO,Parent_menu_NO)
			{
				for(i=1;i<=menu_num;i++)
					{
						if((i == menu_NO)||(i == Parent_menu_NO))
							document.all["menu"+i].style.display="";
						else
							document.all["menu"+i].style.display="none";
					}	
	
			}
		
function disable_all()
	{
		for(i=1;i<=menu_num;i++)
			{
				document.all["menu"+i].style.display="none";
			}
	}

function showLayer( layerID ){
	var layer = document.getElementById( layerID );
	layer.style.visibility = "visible";
}	

function hideLayer( layerID ){
	var layer = document.getElementById( layerID );
	layer.style.visibility = "hidden";
}

function borderit(which,color){  
//if IE 4+ or NS 6+  
if (document.all||document.getElementById){  
which.style.borderColor=color  
}  
}

function OMOver(OMO){OMO.style.backgroundColor='#FFCC00';}
function OMOut(OMO){OMO.style.backgroundColor='';}

function gotoLive(){
	window.open("http://www.nchc.org.tw/chitchat/index.php", "gotoLive", "top=150,left=150,width=623,height=442,location=yes,toolbar=yes,resizable=no,directories=0,status=0")
	}
	
function openVIDEO(sn, videoID){
	window.open("http://www.nchc.org.tw/web_template/2005/1024/htdocs/news/openVideo.php?sn=" + sn + "&videoID=" + videoID, "VIDEO", "width=360, height=320, toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizeable=no")
	}
	
function openFILE(sn, fileID){
	window.open("http://www.nchc.org.tw/web_template/2005/1024/htdocs/news/openFile.php?sn=" + sn + "&fileID=" + fileID, "FILE", "width=360, height=120, toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizeable=no")
	}
	
function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function show_bulletin(layer_id){
	var obj;
	var div_name = layer_id;
	obj = MM_findObj(div_name);	
	obj.style.display = 'block';
}

function close_bulletin(layer_id){
	var obj;
	var div_name = layer_id;
	obj = MM_findObj(div_name);
	obj.style.display = 'none';
	obj.style.visibility = 'hidden';
}	