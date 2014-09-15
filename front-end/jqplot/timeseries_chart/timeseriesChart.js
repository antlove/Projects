$(document).ready(function(){  
      var line1=[['2008-09-30', 4], ['2008-10-30', 6.5], ['2008-11-30', 5.7], ['2008-12-30', 9],  
                 ['2009-01-30', 8.2], ['2009-02-28', 7.6], ['2009-03-30', 11.4], ['2009-04-30', 16.2],   
                 ['2009-05-30', 21.8], ['2009-06-30', 19.3], ['2009-07-30', 29.7], ['2009-08-30', 36.7],  
                 ['2009-09-30', 38.7], ['2009-10-30', 33.7], ['2009-11-30', 49.7], ['2009-12-30', 62.7]];   
                 
      var line2 = [['2008-09-30', 41], ['2008-10-30', 61.5], ['2008-11-30', 51.7], ['2008-12-30', 9]];  

      var plot1 = $.jqplot('chart', [line1,line2 ], {  
        title:'Data Point Highlighting',  
        axes:{  
            xaxis:{  
                renderer:$.jqplot.DateAxisRenderer,  
                tickOptions:{  
                    formatString:'%Y-%m-%#d'  
                },  
                label:'x label value ... '
            },  
            yaxis:{  
                tickOptions:{  
                      formatString:'%.0f',  
                      angle:-30   
                },  
                labelRenderer: $.jqplot.CanvasAxisLabelRenderer,  
                    labelOptions:{                   
                    fontFamily:'Helvetica',                   
                    fontSize: '14pt'              
                },   
                label:'y label value ... ',
                min:0  // set the min value for the y axis
            }  
        },  
        highlighter: {  
            show: true,  
            sizeAdjust: 7.5,  
            showMarker:true,  
            tooltipAxes: 'y',  
            yvalues: 4,  
            formatString:'<table class="jqplot-highlighter"><tr><td>value:</td><td>%s</td></tr></table>'  
  
        } ,
        series:[   
              {},   
              {  
                showLine:false,   
                markerOptions: { size: 7}  
              }  
         ]  
      });  
});  