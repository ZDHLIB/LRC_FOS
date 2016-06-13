function check()
{
	var netMedia=document.addNetMediaForm.netMedia;
	var newNetMedia=document.addNetMediaForm.newNetMedia;

	if(newNetMedia.value=="")
	{
		alert("新增网路媒体类别不能为空！");
		return(false);
	}
	else
	{
		for(var i=0;i<netMedia.length;i++)
		{
			if(newNetMedia.value==netMedia[i].value)
			{
				alert("该网络媒体类别已存在！");
				return(false);
			}
		}
	}

	return(true);
}

