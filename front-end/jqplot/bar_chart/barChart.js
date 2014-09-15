$(document).ready(function(){  
    var barData = [[2,"a"], [4,"b"], [6,"c"], [3,"d"]]  ;
    $.jqplot('chart', [barData], {  
        title:"this is the title ... ",  
        seriesDefaults: {  
            renderer:$.jqplot.BarRenderer,   
            rendererOptions: {  
                barDirection: 'horizontal'  
            }  
        },  
        axes: {  
            xaxis: {  
                label:"the x line ... "  
            },  
            yaxis: {  
                labelRenderer: $.jqplot.CanvasAxisLabelRenderer,  
                renderer: $.jqplot.CategoryAxisRenderer,  
                label:'the y line ... '  
            }  
        }  
    });  
});  