function check()
{
	var language=document.addLanguageForm.language;
	var newLanguage=document.addLanguageForm.newLanguage;

	if(newLanguage.value=="")
	{
		alert("新增语种不能为空！");
		return(false);
	}
	else
	{
		for(var i=0;i<language.length;i++)
		{
			if(newLanguage.value==language[i].value)
			{
				alert("该语种已存在！");
				return(false);
			}
		}
	}

	return(true);
}

