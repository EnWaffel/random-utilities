---@class State
_state = {
    create = function() end,
    update = function(delta) end,
    destroy = function() end
}

---@class Sound
_sound = {
    play = function() end,
    pause = function() end,
    stop = function() end,
    ---@return number
    getPosition = function() end
}

---@class GML
GML = {
    ---@param state State
    ---@param windowTitle string
    ---@param windowWidth number
    ---@param windowHeight number
    ---@return boolean
    init = function(state, windowTitle, windowWidth, windowHeight) return true end,
    ---@param path string
    ---@return Sound
    loadSound = function(path) return _sound end,
    ---@param status number
    exit = function(status) end
}