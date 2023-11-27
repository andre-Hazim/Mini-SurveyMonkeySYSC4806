$(document).ready(function() {
    var formId = $(document).find("#formId_span").data("backend-id")
    $.ajax({
        type: 'GET',
        url: '/getForm/' + formId,
        success: function(data) {
            // Handle the form information
            console.log('Form Information:', data);
            injectGraphs(data)

        },
        error: function(error) {
            // Handle errors
            console.error('Error retrieving form information:', error);
        }
    });

    function injectGraphs(form) {
        console.log("In inject graphs")
        console.log(form)
        const questionsContainer = $("#graphsContainer")

        $.each(form.visualizations, function(index, graph) {

            const visual_div = $("<div>").attr({id: "chart-" + (graph.fieldName).replace(" ", "-")})
            questionsContainer.append(visual_div)
            visual_div.append("<h3> Question " + (index + 1) + ": " + graph.fieldName + "</h3>")
            var chart_id = "chart" + (index + 1)

            if (graph.visualizationType === "HISTOGRAMGRAPH") {
                //drawBarGraph(form.graph[field.id])
                var canvas = $('<canvas id=' + chart_id +' width="1000" height="600"></canvas>');
                var ctx = canvas[0].getContext('2d');
                visual_div.append(canvas);
                drawBarGraph(ctx, graph)
            }
            else if (graph.visualizationType === "PIEGRAPH"){
                var canvas = $('<canvas id=' + chart_id +' width="1000" height="600"></canvas>');
                var ctx = canvas[0].getContext('2d');
                visual_div.append(canvas);
                drawPieGraph(ctx, graph);
            } else if (graph.visualizationType === "TEXT") {
                displayTextResponses(visual_div, graph)
            }
        })
    }

    function displayTextResponses(visual_div, graph) {
        console.log("in display text responses")
        var table = $("<table>").css({
            'border-collapse': 'collapse',
            'width': '100%',
            'border': '1px solid #ddd',
            'margin-top': '10px',
            'max-height': '200px',
            'overflow-y': 'auto' // vertical scrolling
        });

        graph.textResponses.forEach(function(response) {
            var row = $("<tr>").css('border-bottom', '1px solid #ddd');
            var cell = $("<td>").css({
                'border': '1px solid #ddd',
                'padding': '8px',
                'text-align': 'left'
            }).text(response);
            row.append(cell);
            table.append(row);
        });

        visual_div.append(table, '<br>', '<br>');
    }

    function drawBarGraph(ctx, graph) {
        const randColor = getRandomColor(graph.yData.length);

        var graph_name = graph.fieldName + " "

        if (graph.upperBound != null) {
            graph_name += "Upper Bound: " + graph.upperBound + " "
        }

        if (graph.lowerBound != null) {
            graph_name += "Lower Bound: " + graph.lowerBound
        }

        var myChart = new Chart(ctx, {
            type: 'bar', // or 'line', 'pie', etc.
            data: {
                labels: graph.xLabels,
                datasets: [{
                    label: 'Answers',
                    data: graph.yData,
                    backgroundColor: randColor,
                    borderColor: randColor,
                    borderWidth: 1
                }]
            },
            options: {
                maintainAspectRatio: false, // Disable aspect ratio
                responsive: false, // Make the chart responsive
                scales: {
                    y: {
                        beginAtZero: true,
                        scaleLabel: {
                            display: true,
                            labelString: "# of Responses"
                        }
                    },
                    x: {
                        scaleLabel: {
                            display: true,
                            labelString: "Values"
                        }
                    }
                },
                plugins: {
                    title: {
                        display: true,
                        text: graph_name,
                        fontSize: 16
                    }
                }
            }
        });
        console.log("end bar")
        myChart.update();
    }

    function drawPieGraph(ctx,graph) {
        console.log("in pie")
        console.log("graph")

        var myChart = new Chart(ctx, {
            type: 'pie', // or 'line', 'pie', etc.
            data: {
                labels: graph.xLabels,
                datasets: [{
                    data: graph.yData,
                    backgroundColor: getRandomColor(graph.yData.length),
                }]
            },
            options: {
                maintainAspectRatio: false, // Disable aspect ratio
                responsive: false, // Make the chart responsive
            }
        });
        console.log("end pie")
        myChart.update();
    }

    function getRandomColor(length) {
        const colors = []
        for(let l = 0; l<length; l++){
            const letters = '0123456789ABCDEF';
            let color = '#';
            for (let i = 0; i < 6; i++) {
                color += letters[Math.floor(Math.random() * 16)];
            }
            colors.push(color);
        }
        return colors;

    }
})