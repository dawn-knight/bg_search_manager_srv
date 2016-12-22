    	
var lastFaqClick=null;
			window.onload=function(){
			  var faq=document.getElementById("faq");
			  var dls=faq.getElementsByTagName("dl");
			  for (var i=0,dl;dl=dls[i];i++){
			    var dt=dl.getElementsByTagName("dt")[0];//ȡ�ñ���
			     dt.id = "faq_dt_"+(Math.random()*100);
			     dt.onclick=function(){
			       var p=this.parentNode;//ȡ�ø��ڵ�
			        if (lastFaqClick!=null&&lastFaqClick.id!=this.id){
			          var dds=lastFaqClick.parentNode.getElementsByTagName("dd");
			          for (var i=0,dd;dd=dds[i];i++){
			            dd.style.display='none';
			          }
			        }
			        lastFaqClick=this;
			        var dds=p.getElementsByTagName("dd");//ȡ�ö�Ӧ�ӽڵ㣬Ҳ����˵������
			        var tmpDisplay='none';
			        if (gs(dds[0],'display')=='none'){
			          tmpDisplay='block';
			        }
			        for (var i=0;i<dds.length;i++){
			          dds[i].style.display=tmpDisplay;
			        }
			      }
			  }
			}

			function gs(d,a){
			  if (d.currentStyle){
			    var curVal=d.currentStyle[a]
			  }else{
			    var curVal=document.defaultView.getComputedStyle(d, null)[a]
			  }
			  return curVal;
			}

        $(function(){
				$.fn.datebox.defaults.formatter = function(date){ 
			        var y = date.getFullYear(); 
			        var m = date.getMonth()+1; 
			        var d = date.getDate(); 
			        return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d); 
			    }; 
			    $.fn.datebox.defaults.parser = function(s){ 
			        if (!s) return new Date(); 
			        var ss = s.split('-'); 
			        var y = parseInt(ss[0],10); 
			        var m = parseInt(ss[1],10); 
			        var d = parseInt(ss[2],10); 
			        if (!isNaN(y) && !isNaN(m) && !isNaN(d)){ 
			            return new Date(y,m-1,d); 
			        } else { 
			            return new Date(); 
			        } 
			    };
				
				Date.prototype.format = function(format) {
					var o = {
					"M+" : this.getMonth() + 1, // month
					"d+" : this.getDate(), // day
					"h+" : this.getHours(), // hour
					"m+" : this.getMinutes(), // minute
					"s+" : this.getSeconds(), // second
					"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
					"S" : this.getMilliseconds()
					};
					if (/(y+)/.test(format)) {
					format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
					}
					for ( var k in o) {
					if (new RegExp("(" + k + ")").test(format)) {
					format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
					}
					}
					return format;
					};	
        });