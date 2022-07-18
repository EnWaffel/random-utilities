local gml = require("GML_GAME")
local _window = require("Window")
local _renderer = require("Renderer")
local renderer = _renderer.new()
local newWindow = _window.new(renderer)

newWindow.onRender = function()
    renderer.glbegin(_renderer.glstate.GL_QUADS)
    renderer.vertex2(0, 0)
    renderer.vertex2(100, 0)
    renderer.vertex2(100, 100)
    renderer.vertex2(0, 100)
    renderer.glend();
end