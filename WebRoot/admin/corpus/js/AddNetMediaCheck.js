function check()
{
	var netMedia=document.addNetMediaForm.netMedia;
	var newNetMedia=document.addNetMediaForm.newNetMedia;

	if(newNetMedia.value=="")
	{
		alert("������·ý�������Ϊ�գ�");
		return(false);
	}
	else
	{
		for(var i=0;i<netMedia.length;i++)
		{
			if(newNetMedia.value==netMedia[i].value)
			{
				alert("������ý������Ѵ��ڣ�");
				return(false);
			}
		}
	}

	return(true);
}

