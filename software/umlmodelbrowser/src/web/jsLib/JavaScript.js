function findFrameByName(strName) {
    return findFrame(top, strName);
  }

function findFrame(doc, strName) {
   if (doc.frames.length == 0) return;

   for (var i = 0; i != doc.frames.length; i++)
     if (doc.frames[i].name == strName)
       return doc.frames[i];
     else {
       var frm = findFrame(doc.frames[i].window, strName);

       if ( frm  )
         return frm;
     }

   return top;
}
function performAction_not_used(urlParams){
  var frm = findFrameByName('body');
  document.body.style.cursor = "wait";
  frm.document.body.style.cursor = "wait";
  frm.document.location = "search?"+urlParams + "<%= callerParams %>";
}


function classDetailsAction(urlParams){
    var frm = findFrameByName('body');
    document.body.style.cursor = "wait";
    frm.document.body.style.cursor = "wait";
    frm.document.location = "/umlbrowser/umlSearchAction.do?method=attributeSearch&"+urlParams;
}


function newBrowserWin(url,windowName,nwidth,nheight)
{
  if (nwidth == null) {
    nwidth = 440;
  }

  if (nheight == null) {
    nheight = 300;
  }

  screenx = (screen.availWidth - nwidth) / 2;
  screeny =  (screen.availHeight - nheight) / 2;
  var hWnd = window.open(url,windowName,"toolbar=yes,width="+nwidth+",height="+nheight+",screenx="+screenx+",screeny="+screeny+",resizable=yes,scrollbars=yes,menubar=yes,directories=yes, location=yes");
  hWnd.focus();
}
