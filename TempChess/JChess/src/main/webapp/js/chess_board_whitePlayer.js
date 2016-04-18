var NUMBER_OF_COLS = 8,
	NUMBER_OF_ROWS = 8,
	BLOCK_SIZE = 100;
var HD_HEIGHT = 1080;


var BLOCK_COLOUR_1 = '#f0d9b5',
    BLOCK_COLOUR_2 = '#b58863',
	HIGHLIGHT_COLOUR = '#bbd26b';

var canvasCoef = 1;
var piecePositions = null;
var req = new XMLHttpRequest();
var answer;  // TODO: replace to JSON

var PIECE_PAWN = 0,
	PIECE_CASTLE = 1,
	PIECE_ROUKE = 2,
	PIECE_BISHOP = 3,
	PIECE_QUEEN = 4,
	PIECE_KING = 5,
	IN_PLAY = 0,
	TAKEN = 1,
	pieces = null,
	ctx = null,
	json = null,
	jsonToServer = null,
	canvas = null,
	BLACK_TEAM = 0,
	WHITE_TEAM = 1,
	SELECT_LINE_WIDTH = 3,
	currentTurn = WHITE_TEAM,
	selectedPiece = null;


function convertToStdCoordinate(col,row){
	if(col === 7) return 'H' + (8 - row);
	if(col === 6) return 'G' + (8 - row);
	if(col === 5) return 'F' + (8 - row);
	if(col === 4) return 'E' + (8 - row);
	if(col === 3) return 'D' + (8 - row);
	if(col === 2) return 'C' + (8 - row);
	if(col === 1) return 'B' + (8 - row);
	if(col === 0) return 'A' + (8 - row);
}

function sendToServer(json){
    $.post('main',json,function(data){
        answer = data;
    })
}

function screenToBlock(x, y) {
	var block =  {
		"row": Math.floor(y / BLOCK_SIZE),
		"col": Math.floor(x / BLOCK_SIZE)
	};

	return block;
}

function getPieceAtBlockForTeam(teamOfPieces, clickedBlock) {

	var curPiece = null,
		iPieceCounter = 0,
		pieceAtBlock = null;

	for (iPieceCounter = 0; iPieceCounter < teamOfPieces.length; iPieceCounter++) {

		curPiece = teamOfPieces[iPieceCounter];

		if (curPiece.status === IN_PLAY &&
				curPiece.col === clickedBlock.col &&
				curPiece.row === clickedBlock.row) {
			curPiece.position = iPieceCounter;

			pieceAtBlock = curPiece;
			iPieceCounter = teamOfPieces.length;
		}
	}

	return pieceAtBlock;
}

function blockOccupiedByEnemy(clickedBlock) {
	return getPieceAtBlockForTeam(json.black, clickedBlock);
}


function blockOccupied(clickedBlock) {
	//var pieceAtBlock = getPieceAtBlockForTeam(json.black, clickedBlock);

	//if (pieceAtBlock === null) {
		pieceAtBlock = getPieceAtBlockForTeam(json.white, clickedBlock);
	//}

	return (pieceAtBlock !== null);
}



function canSelectedMoveToBlock(selectedPiece, clickedBlock, enemyPiece) {

    var jsonToServer;
    jsonToServer = {
        type: "Move",
		from: convertToStdCoordinate(selectedPiece.col,selectedPiece.row),
		to: convertToStdCoordinate(clickedBlock.col, clickedBlock.row)
    }
    var str = JSON.stringify(jsonToServer)
    alert(str);
    //sendToServer(jsonToServer);
    answer = {
        response: 'OK'                // TODO: Replace answer. This is cap
    }
    return (answer.response === 'OK');


}

function getPieceAtBlock(clickedBlock) {

	var team = json.white;

	return getPieceAtBlockForTeam(team, clickedBlock);
}

function getBlockColour(iRowCounter, iBlockCounter) {
	var cStartColour;

	// Alternate the block colour
	if (iRowCounter % 2) {
		cStartColour = (iBlockCounter % 2 ? BLOCK_COLOUR_1 : BLOCK_COLOUR_2);
	} else {
		cStartColour = (iBlockCounter % 2 ? BLOCK_COLOUR_2 : BLOCK_COLOUR_1);
	}

	return cStartColour;
}

function drawBlock(iRowCounter, iBlockCounter) {
	// Set the background
	ctx.fillStyle = getBlockColour(iRowCounter, iBlockCounter);


	// Draw rectangle for the background
	ctx.fillRect(iRowCounter * BLOCK_SIZE, iBlockCounter * BLOCK_SIZE,
	BLOCK_SIZE, BLOCK_SIZE);
	ctx.lineWidth = 3;
	ctx.strokeStyle = "#000000";
    ctx.strokeRect(0, 0,
    		NUMBER_OF_ROWS * BLOCK_SIZE,
    		NUMBER_OF_COLS * BLOCK_SIZE);

	ctx.stroke();
}

function getImageCoords(pieceCode, bBlackTeam) {

	var imageCoords =  {
		"x": pieceCode * 100,
		"y": (bBlackTeam ? 0 : 100)
	};

	return imageCoords;
}

function drawPiece(curPiece, bBlackTeam) {

	var imageCoords = getImageCoords(curPiece.piece, bBlackTeam);

	// Draw the piece onto the canvas
	ctx.drawImage(pieces,
		imageCoords.x, imageCoords.y,
		100, 100,
		curPiece.col * BLOCK_SIZE, curPiece.row * BLOCK_SIZE,
		BLOCK_SIZE, BLOCK_SIZE);
}

function removeSelection(selectedPiece) {
	drawBlock(selectedPiece.col, selectedPiece.row);
	drawPiece(selectedPiece, (currentTurn === BLACK_TEAM));
}

function drawTeamOfPieces(teamOfPieces, bBlackTeam) {
	var iPieceCounter;

	// Loop through each piece and draw it on the canvas
	for (iPieceCounter = 0; iPieceCounter < teamOfPieces.length; iPieceCounter++) {
		drawPiece(teamOfPieces[iPieceCounter], bBlackTeam);
	}
}

function drawPieces() {
	drawTeamOfPieces(json.black, true);
	drawTeamOfPieces(json.white, false);
}

function drawRow(iRowCounter) {
	var iBlockCounter;

	// Draw 8 block left to right
	for (iBlockCounter = 0; iBlockCounter < NUMBER_OF_ROWS; iBlockCounter++) {
		drawBlock(iRowCounter, iBlockCounter);
	}
}

function drawBoard() {
	var iRowCounter;

	for (iRowCounter = 0; iRowCounter < NUMBER_OF_ROWS; iRowCounter++) {
		drawRow(iRowCounter);
	}

	// Draw outline
	ctx.lineWidth = 3;
	ctx.strokeRect(0, 0,
		NUMBER_OF_ROWS * BLOCK_SIZE,
		NUMBER_OF_COLS * BLOCK_SIZE);
}

function defaultPositions() {
	json = {
		"black":
			[
				{
					"piece": PIECE_CASTLE,
					"row": 0,
					"col": 0,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_ROUKE,
					"row": 0,
					"col": 1,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_BISHOP,
					"row": 0,
					"col": 2,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_KING,
					"row": 0,
					"col": 4,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_QUEEN,
					"row": 0,
					"col": 3,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_BISHOP,
					"row": 0,
					"col": 5,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_ROUKE,
					"row": 0,
					"col": 6,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_CASTLE,
					"row": 0,
					"col": 7,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 1,
					"col": 0,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 1,
					"col": 1,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 1,
					"col": 2,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 1,
					"col": 3,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 1,
					"col": 4,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 1,
					"col": 5,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 1,
					"col": 6,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 1,
					"col": 7,
					"status": IN_PLAY
				}
			],
		"white":
			[
				{
					"piece": PIECE_CASTLE,
					"row": 7,
					"col": 0,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_ROUKE,
					"row": 7,
					"col": 1,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_BISHOP,
					"row": 7,
					"col": 2,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_KING,
					"row": 7,
					"col": 4,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_QUEEN,
					"row": 7,
					"col": 3,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_BISHOP,
					"row": 7,
					"col": 5,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_ROUKE,
					"row": 7,
					"col": 6,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_CASTLE,
					"row": 7,
					"col": 7,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 6,
					"col": 0,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 6,
					"col": 1,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 6,
					"col": 2,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 6,
					"col": 3,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 6,
					"col": 4,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 6,
					"col": 5,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 6,
					"col": 6,
					"status": IN_PLAY
				},
				{
					"piece": PIECE_PAWN,
					"row": 6,
					"col": 7,
					"status": IN_PLAY
				}
			]
	};
}

function selectPiece(pieceAtBlock) {
	// Draw outline
	ctx.lineWidth = SELECT_LINE_WIDTH;
	ctx.strokeStyle = HIGHLIGHT_COLOUR;
	ctx.strokeRect((pieceAtBlock.col * BLOCK_SIZE) + SELECT_LINE_WIDTH,
		(pieceAtBlock.row * BLOCK_SIZE) + SELECT_LINE_WIDTH,
		BLOCK_SIZE - (SELECT_LINE_WIDTH * 2),
		BLOCK_SIZE - (SELECT_LINE_WIDTH * 2));

	selectedPiece = pieceAtBlock;
}

function checkIfPieceClicked(clickedBlock) {
	var pieceAtBlock = getPieceAtBlock(clickedBlock);

	if (pieceAtBlock !== null) {
		selectPiece(pieceAtBlock);
	}
}

function movePiece(clickedBlock, enemyPiece) {
	// Clear the block in the original position

	drawBlock(selectedPiece.col, selectedPiece.row);

	var //team = (currentTurn === WHITE_TEAM ? json.white : json.black),
		team = json.white;
		//opposite = (currentTurn !== WHITE_TEAM ? json.white : json.black);
        opposite = json.black;

	team[selectedPiece.position].col = clickedBlock.col;
	team[selectedPiece.position].row = clickedBlock.row;

	if (enemyPiece !== null) {
		// Clear the piece your about to take
		drawBlock(enemyPiece.col, enemyPiece.row);
		opposite[enemyPiece.position].status = TAKEN;
	}

	// Draw the piece in the new position
	drawPiece(selectedPiece, BLACK_TEAM );


	selectedPiece = null;
}

function processMove(clickedBlock) {
	var pieceAtBlock = getPieceAtBlock(clickedBlock),
		enemyPiece = blockOccupiedByEnemy(clickedBlock);

	if (pieceAtBlock !== null) {
		removeSelection(selectedPiece);
		checkIfPieceClicked(clickedBlock);
	} else if (canSelectedMoveToBlock(selectedPiece, clickedBlock, enemyPiece) === true) {
		movePiece(clickedBlock, enemyPiece);
	}
	//WaitingEnemyMove();
}

function WaitingEnemyMove(){
    alert('rara');
	$.post('main',{action: 'Wai'})
}

function board_click(ev) {
	var x = ev.clientX - canvas.offsetLeft,
		y = ev.clientY - canvas.offsetTop,
		clickedBlock = screenToBlock(x, y);

	if (selectedPiece === null) {
		checkIfPieceClicked(clickedBlock);
	} else {
		processMove(clickedBlock);
	}

}

function draw() {
	// Main entry point got the HTML5 chess board example

	canvas = document.getElementById('chess');

	// Canvas supported?
	if (canvas.getContext) {
        ctx = canvas.getContext('2d');

        canvasCoef = screen.availHeight / HD_HEIGHT ;

        if (canvas.height > screen.availHeight) {
            canvas.height = canvas.height * canvasCoef;
            canvas.width = canvas.width * canvasCoef;
        }

        // Calculate the precise block size
        BLOCK_SIZE = canvas.height / NUMBER_OF_ROWS;


        // Draw the background
        drawBoard();

		defaultPositions();

		// Draw pieces
		pieces = new Image();
		pieces.src = 'pieces.png';
		pieces.onload = drawPieces;



		canvas.addEventListener('click', board_click, false);
	} else {
		alert("Canvas not supported!");
	}



}