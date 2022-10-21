local sound
local sound1
local totalTime = 0

local function _create()
	sound = GML.loadSound("test.wav")
	sound1 = GML.loadSound("test1.wav")
	sound.play()
	sound1.play()
end

local function _update(delta)
	totalTime = totalTime + 1
end

local function _destroy()
	
end

local titleState = {create = _create, update = _update, destroy = _destroy}
GML.init(titleState, "hi", 1280, 720)
