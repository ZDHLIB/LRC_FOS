function check()
{
	var category=document.addCategoryForm.category;
	var newCategory=document.addCategoryForm.newCategory;

	if(newCategory.value=="")
	{
		alert("���������Ϊ�գ�");
		return(false);
	}
	else
	{
		for(var i=0;i<category.length;i++)
		{
			if(newCategory.value==category[i].value)
			{
				alert("������Ѵ��ڣ�");
				return(false);
			}
		}
	}

	return(true);
}

