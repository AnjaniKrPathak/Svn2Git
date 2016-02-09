var mastertabvar=new Object()
mastertabvar.baseopacity=0
mastertabvar.browserdetect=""

	function showsubM(id)
{
	var subid='submenu'+id;
	document.getElementById('submenu1').style.display="none";
	document.getElementById('submenu2').style.display="none";
	document.getElementById('submenu3').style.display="none";
	document.getElementById('submenu4').style.display="none";
	document.getElementById('submenu5').style.display="none";
	document.getElementById('submenu6').style.display="none";
	document.getElementById('submenu7').style.display="none";
	document.getElementById(subid).style.display="block";
	/*funSelected(id);*/
	
	}
	
function showHsubM(id)
{
	var subid='submenu'+id;
	document.getElementById('submenu1').style.display="none";
	document.getElementById('submenu2').style.display="none";
	document.getElementById('submenu3').style.display="none";
	document.getElementById(subid).style.display="block";

	}
	


function hidesubM()
{
	var id=document.getElementById('txtMTabIndex').value;
/*	alert(id);*/
	var subid='submenu'+id;	
	var mid='menu'+id;

	
	document.getElementById('menu1').style.backgroundColor="#F7F7F7";
	document.getElementById('menu2').style.backgroundColor="#F7F7F7";
	document.getElementById('menu3').style.backgroundColor="#F7F7F7";
	document.getElementById('menu4').style.backgroundColor="#F7F7F7";
	document.getElementById('menu5').style.backgroundColor="#F7F7F7";
    document.getElementById('menu6').style.backgroundColor="#F7F7F7";
    document.getElementById('menu7').style.backgroundColor="#F7F7F7";
	document.getElementById(mid).style.backgroundColor="#53B4E6";
	 document.getElementById(subid).style.display="block";
	 /*if(id!=7)
	  document.getElementById('submenu7').style.display="none";*/
}


function hideHsubM()
{
	var id=document.getElementById('txtMTabIndex').value;
/*	alert(id);*/
	var subid='submenu'+id;
	var mid='menu'+id;
	document.getElementById('menu1').style.backgroundColor="#F7F7F7";
	document.getElementById('menu2').style.backgroundColor="#F7F7F7";
	document.getElementById('menu3').style.backgroundColor="#F7F7F7";
	document.getElementById(mid).style.backgroundColor="#53B4E6";
	 document.getElementById(subid).style.display="block";
	 document.getElementById('submenu7').style.display="none";
	}

	function funSelected(id)
	{
		var tid=document.getElementById('txtMTabIndex').value;
		var subid='menu'+id;
		 var showid='submenu'+tid;
		 
		document.getElementById('menu1').style.backgroundColor="#F7F7F7";	
		document.getElementById('menu2').style.backgroundColor="#F7F7F7";
		document.getElementById('menu3').style.backgroundColor="#F7F7F7";
		document.getElementById('menu4').style.backgroundColor="#F7F7F7";
		document.getElementById('menu5').style.backgroundColor="#F7F7F7";
		document.getElementById('menu6').style.backgroundColor="#F7F7F7";
		document.getElementById('menu7').style.backgroundColor="#F7F7F7";
		document.getElementById(subid).style.backgroundColor="#53B4E6";
		document.getElementById(showid).style.display="block";
		if(subid=!7)
			document.getElementById('submenu7').style.display="none";
		
	}
	
	
	function funHSelected(id)
	{
		var tid=document.getElementById('txtMTabIndex').value;
		var subid='menu'+id;
		var showid='submenu'+tid;
		
		document.getElementById('menu1').style.backgroundColor="#F7F7F7";
		document.getElementById('menu2').style.backgroundColor="#F7F7F7";
		document.getElementById('menu3').style.backgroundColor="#F7F7F7";
		document.getElementById(subid).style.backgroundColor="#53B4E6";
		
		if(id==tid)
		{	 
		 document.getElementById(showid).style.display="block";
		 document.getElementById('submenu7').style.display="none";
		}
	else
		{			
		 document.getElementById(showid).style.display="none";
		 document.getElementById('submenu7').style.display="block";		
		 
		}
	}
	
	function changeIndex()
	{	
		var id=document.getElementById('txtMTabIndex').value;	
		var indexId='menu'+id;
		var subid='submenu'+id;
		
		var submenuid =document.getElementById('txtpSubMenuIndex').value;
		submenuid='patientMenuForm:submenu'+submenuid;
		document.getElementById(submenuid).style.color="#F7F7F7";
		
		document.getElementById('menu1').style.backgroundColor="#F7F7F7";
		document.getElementById('menu2').style.backgroundColor="#F7F7F7";
		document.getElementById('menu3').style.backgroundColor="#F7F7F7";
		document.getElementById('menu4').style.backgroundColor="#F7F7F7";
		document.getElementById('menu5').style.backgroundColor="#F7F7F7";
		document.getElementById('menu6').style.backgroundColor="#F7F7F7";
		document.getElementById(indexId).style.backgroundColor="#53B4E6";
	
		document.getElementById(subid).style.display="block";
		if(id==7)
			{
			document.getElementById('submenu7').style.display="block";
			}
		else
			{
			document.getElementById('submenu7').style.display="none";
			}
		
		
	}
	
	
	function changeHIndex()
	{
		var id=document.getElementById('txtMTabIndex').value;		
		var indexId='menu'+id;
		var subid='submenu'+id;
		document.getElementById('menu1').style.backgroundColor="#F7F7F7";
		document.getElementById('menu2').style.backgroundColor="#F7F7F7";
		document.getElementById('menu3').style.backgroundColor="#F7F7F7";
		document.getElementById(indexId).style.backgroundColor="#53B4E6";
		document.getElementById(subid).style.display="block";	
	}
	
	function changeBorderColor()
	{
		
		document.getElementById('menu1').style.borderBottomColor="#53B4E6";
		document.getElementById('menu2').style.borderBottomColor="#53B4E6";
		document.getElementById('menu3').style.borderBottomColor="#53B4E6";
		document.getElementById('menu4').style.borderBottomColor="#53B4E6";
		document.getElementById('menu5').style.borderBottomColor="#53B4E6";
		document.getElementById('menu6').style.borderBottomColor="#53B4E6";
	}
	
	/* Menu code for home */

	
		
		
		
		function changeHBorderColor()
		{
			
			document.getElementById('menu1').style.borderBottomColor="#53B4E6";
			document.getElementById('menu2').style.borderBottomColor="#53B4E6";
			document.getElementById('menu3').style.borderBottomColor="#53B4E6";
		}
		
		
		function changeLeftMenuIndex(screen)
		{
		
		var screenName=screen;
				
			if(screenName=='med')
				{
				var sId=document.getElementById('txtSTabIndex').value;			
				var subId='lefpmenu'+sId;
				
				document.getElementById('lefpmenu1').style.backgroundColor="#F7F7F7";
				document.getElementById('lefpmenu2').style.backgroundColor="#F7F7F7";
				document.getElementById('lefpmenu3').style.backgroundColor="#F7F7F7";
				document.getElementById('lefpmenu4').style.backgroundColor="#F7F7F7";
				document.getElementById('lefpmenu5').style.backgroundColor="#F7F7F7";
				document.getElementById(subId).style.backgroundColor="#53B4E6";
				
				document.getElementById('lefpmenu1').style.borderColor="#CCCCCC";
				document.getElementById('lefpmenu2').style.borderColor="#CCCCCC";
				document.getElementById('lefpmenu3').style.borderColor="#CCCCCC";
				document.getElementById('lefpmenu4').style.borderColor="#CCCCCC";
				document.getElementById('lefpmenu5').style.borderColor="#CCCCCC";
				document.getElementById(subId).style.borderColor="#53B4E6";
				
				}
			else if(screenName=='mymedication')
				{
				var smId=document.getElementById('txtCTabIndex').value;			
				var subId='lefpmenu'+smId;
			
				document.getElementById('lefpmenu1').style.backgroundColor="#F7F7F7";
				document.getElementById('lefpmenu2').style.backgroundColor="#F7F7F7";/*
				document.getElementById('lefpmenu3').style.borderColor="#F7F7F7";*/
				document.getElementById(subId).style.backgroundColor="#53B4E6";
				
				document.getElementById('lefpmenu1').style.borderColor="#CCCCCC";
				document.getElementById('lefpmenu2').style.borderColor="#CCCCCC";/*
				document.getElementById('lefpmenu3').style.borderColor="#CCCCCC";*/
				document.getElementById(subId).style.borderColor="#53B4E6";
				
				}
			else if(screenName=='home')
			{
			var hsmId=document.getElementById('txtHSMTabIndex').value;			
			var subId='lefpmenu'+hsmId;
			
			document.getElementById('lefpmenu1').style.backgroundColor="#F7F7F7";
			document.getElementById('lefpmenu2').style.backgroundColor="#F7F7F7";
			document.getElementById('lefpmenu3').style.backgroundColor="#F7F7F7";
			document.getElementById(subId).style.backgroundColor="#53B4E6";
			
			document.getElementById('lefpmenu1').style.borderColor="#CCCCCC";
			document.getElementById('lefpmenu2').style.borderColor="#CCCCCC";
			document.getElementById('lefpmenu3').style.borderColor="#CCCCCC";
			document.getElementById(subId).style.borderColor="#53B4E6";
			
			}
		}
	
	
	