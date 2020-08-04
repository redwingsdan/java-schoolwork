var shapeCounter;
var canvasWidth;
var canvasHeight;
var canvas;
var canvas2D;
var balls;
var fps;
var time;
var timer;
var go;

function Ball(	initColor, initRadius, 
		initCenterX, initCenterY,
		initXinc, initYinc)
{
	this.color = initColor;
	this.radius = initRadius;
	this.centerX = initCenterX;
	this.centerY = initCenterY;
	this.xInc = initXinc;
	this.yInc = initYinc;
}


function init()
{
	shapeCounter = 0;
	balls = new Array();
	balls[0] = new Ball("#990099", 30,  30,  30,  10,  10);
	balls[1] = new Ball("#999900", 30, 530,  30, -10,  10);
	balls[2] = new Ball("#009999", 30, 120, 220, -10, -10);
	balls[3] = new Ball("#991122", 30, 530, 400,  10, -10);
	balls[4] = new Ball("#229911", 30, 330, 120, -10, -10);
	balls[5] = new Ball("#112299", 30, 700, 650,  10,  10);
	canvasWidth = 1280;
	canvasHeight = 720;
	canvas = document.getElementById("myCanvas");  
	canvas2D = canvas.getContext("2d");
	fps = 30;
	timer = null;
	go = true;
	updateFPS();
}

function update()
{
	for (var i = 0; i < balls.length; i++)
	{
		var ball = balls[i];
		ball.centerX += ball.xInc;
		ball.centerY += ball.yInc;
		if ((ball.centerX - ball.radius) <= 0) ball.xInc *= -1;
		if ((ball.centerX + ball.radius) >= canvasWidth) ball.xInc *= -1;
		if ((ball.centerY - ball.radius) <= 0) ball.yInc *= -1;
		if ((ball.centerY + ball.radius) >= canvasHeight) ball.yInc *= -1;
	}
}

function render()
{
	var renderCircles = false;
	var shapeSelect = $("#shapeSelect");
	var shape = shapeSelect.val();
	if (shape === "Circle")
		renderCircles = true;
	var filledCheckbox = $("#filled_checkbox");
	var filled = filledCheckbox.is(':checked');
	var lineThicknessSelect = $("#line_thickness");
	var lineThickness = lineThicknessSelect.val();
	for (var i = 0; i < balls.length; i++)
	{
		var ball = balls[i];
		if (renderCircles)
		{
			canvas2D.beginPath();  
			canvas2D.arc(ball.centerX,ball.centerY,ball.radius,0,2*Math.PI);
			if (filled)
			{
				canvas2D.fillStyle = ball.color;
				canvas2D.fill();
			}
			else
			{
				canvas2D.strokeStyle = ball.color;
				canvas2D.lineWidth = lineThickness;
				canvas2D.stroke();
			}
		}
		else
		{
			var halfLength = ball.radius/2;
			var x = ball.centerX - halfLength;
			var y = ball.centerY - halfLength;
			var length = ball.radius;
			if (filled)
			{
				canvas2D.fillStyle = ball.color;
				canvas2D.fillRect(x, y, length, length);
			}
			else
			{
				canvas2D.beginPath();
				canvas2D.strokeStyle = ball.color;
				canvas2D.lineWidth = lineThickness;
				canvas2D.rect(x, y, length, length);
				canvas2D.stroke();
			}
		}
		shapeCounter++;
	}
	
	// UPDATE THE COUNTER DISPLAY
	var counterSpan = $("#counter");
	var counterText = "" + shapeCounter;
	counterSpan.text(counterText);
}

function step()
{
	if (go)
	{
		update();
		render();
	}
}

function clearCanvas()
{
	canvas2D.clearRect(0, 0, canvasWidth, canvasHeight);
	shapeCounter = 0;
}

function stop()
{
	go = false;
}

function start()
{
	go = true;
}

function slower()
{
	if (fps > 1)
	{
		fps--;
		updateFPS();
	}
}

function faster()
{
	if (fps < 100)
	{
		fps++;
		updateFPS();
	}
}

function updateFPS()
{
	if (timer != null)
		clearInterval(timer);
	time = 1000/fps;

	// WHAT SHOULD WE USE AS OUR UPDATE METHOD?
    //timer = setInterval(?, time);	
	timer = setInterval(time);
	// UPDATE THE FPS DISPLAY
	var fpsSpan = $("#fps");
	var fpsText = "" + fps;
	fpsSpan.text(fpsText);	
}