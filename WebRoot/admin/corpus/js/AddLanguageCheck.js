function check()
{
	var language=document.addLanguageForm.language;
	var newLanguage=document.addLanguageForm.newLanguage;

	if(newLanguage.value=="")
	{
		alert("�������ֲ���Ϊ�գ�");
		return(false);
	}
	else
	{
		for(var i=0;i<language.length;i++)
		{
			if(newLanguage.value==language[i].value)
			{
				alert("�������Ѵ��ڣ�");
				return(false);
			}
		}
	}

	return(true);
}

