/* 
 * This JavaScript file provides methods for clearing the
 * canvas and for rendering Red Box Man.
 */

var canvas;
var gc;
var canvasWidth;
var canvasHeight;
var imageLoaded;
var mousePositionRendering;
var redBoxManImage;
var mouseX;
var mouseY;
var imagesRedBoxManLocations;
var shapesRedBoxManLocations;

function Location(initX, initY) {
    this.x = initX;
    this.y = initY;
}

function init() {
    // GET THE CANVAS SO WE CAN USE IT WHEN WE LIKE
    canvas = document.getElementById("red_box_man_canvas");
    gc = canvas.getContext("2d");
        
    // MAKE SURE THE CANVAS DIMENSIONS ARE 1:1 FOR RENDERING
    canvas.width = canvas.offsetWidth;
    canvas.height = canvas.offsetHeight;
    canvasWidth = canvas.width;
    canvasHeight = canvas.height;
    
        // FOR RENDERING TEXT
    gc.font="32pt Arial";

    // LOAD THE RED BOX MAN IMAGE SO WE CAN RENDER
    // IT TO THE CANVAS WHENEVER WE LIKE
    redBoxManImage = new Image();
    redBoxManImage.onload = function() {
	imageLoaded = true;
    }
    redBoxManImage.src = "./images/RedBoxMan.png";
    
    // BY DEFAULT WE'LL START WITH MOUSE POSITION RENDERING ON
    mousePositionRendering = true;
    
    // HERE'S WHERE WE'LL PUT OUR RENDERING COORDINATES
    imagesRedBoxManLocations = new Array();
    shapesRedBoxManLocations = new Array();
}

function processMouseClick(event) {
    updateMousePosition(event);
    var location = new Location(mouseX, mouseY);
    if (event.shiftKey) {
	shapesRedBoxManLocations.push(location);
	render();
    }
    else if (event.ctrlKey) {
	if (imageLoaded) {
	    imagesRedBoxManLocations.push(location);
	    render();
	}
    }
    else {
	clear();
    }
}

function clearCanvas() {
    gc.clearRect(0, 0, canvasWidth, canvasHeight);
}

function clear() {
    shapesRedBoxManLocations = [];
    imagesRedBoxManLocations = [];
    clearCanvas();
}

function updateMousePosition(event) {
    var rect = canvas.getBoundingClientRect();
    mouseX = event.clientX - rect.left;
    mouseY = event.clientY - rect.top;
    render();
}

function renderShapesRedBoxMan(location) {
    var headColor = "#0000FF";
    var outlineColor = "#000000";
    var eyeColor = "#FFFF00";
    var headW = 115;
    var headH = 88;
    var bodyW = 55;
    var bodyH = 25;
    var legW = 30;
    var legH = 10;
    var feetW = 6;
    var feetH = 6;
    var mouthW = 85;
    var mouthH = 10;
    var eyeW = 30;
    var eyeH = 25;
    
    // DRAW HIS RED HEAD
    gc.fillStyle = headColor;
    gc.fillRect(location.x, location.y, headW, headH);
    gc.beginPath();
    gc.strokeStyle = outlineColor;
    gc.lineWidth = 1;
    gc.rect(location.x, location.y, headW, headH);
    gc.stroke();
    
    gc.fillStyle = outlineColor;
    gc.fillRect(location.x+32, location.y+88, bodyW, bodyH);
    gc.beginPath();
    gc.stroke();
    
    gc.fillStyle = outlineColor;
    gc.fillRect(location.x+45, location.y+112, legW, legH);
    gc.beginPath();
    gc.stroke();
    
    gc.fillStyle = outlineColor;
    gc.fillRect(location.x+40, location.y+122, feetW, feetH);
    gc.beginPath();
    gc.stroke();
    
    gc.fillStyle = outlineColor;
    gc.fillRect(location.x+75, location.y+122, feetW, feetH);
    gc.beginPath();
    gc.stroke();
    
    gc.fillStyle = outlineColor;
    gc.fillRect(location.x+15, location.y+60, mouthW, mouthH);
    gc.beginPath();
    gc.stroke();
    
    gc.fillStyle = eyeColor;
    gc.fillRect(location.x+15, location.y+15, eyeW, eyeH);
    gc.beginPath();
    gc.stroke();
    
    gc.fillStyle = eyeColor;
    gc.fillRect(location.x+75, location.y+15, eyeW, eyeH);
    gc.beginPath();
    gc.stroke();
    
    gc.fillStyle = outlineColor;
    gc.fillRect(location.x+88, location.y+25, feetW, feetH);
    gc.beginPath();
    gc.stroke();
    
    gc.fillStyle = outlineColor;
    gc.fillRect(location.x+30, location.y+25, feetW, feetH);
    gc.beginPath();
    gc.stroke();
    
    // AND THEN DRAW THE REST OF HIM
}

function renderImageRedBoxMan(location) {
    gc.drawImage(redBoxManImage, location.x, location.y);
}

function renderMousePositionInCanvas(event) {
    if (mousePositionRendering) {
	gc.strokeText("(" + mouseX + "," + mouseY + ")", 10, 50);    
    }
}

function toggleMousePositionRendering() {
    mousePositionRendering = !mousePositionRendering;
}

function render() {
    clearCanvas();
    for (var i = 0; i < shapesRedBoxManLocations.length; i++) {
	var location = shapesRedBoxManLocations[i];
	renderShapesRedBoxMan(location);
    }
    for (var j = 0; j < imagesRedBoxManLocations.length; j++) {
	var location = imagesRedBoxManLocations[j];
	renderImageRedBoxMan(location);
    }
    renderMousePositionInCanvas();
}