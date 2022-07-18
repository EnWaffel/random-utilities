Renderer = {}
Renderer.__index = Renderer
Renderer.glstate = {
    GL_LINES = 0,
    GL_QUADS = 1
}

function Renderer.new()
    local newRenderer = {}
    setmetatable(newRenderer, Renderer)

    newRenderer.glbegin = function(glState) end
    newRenderer.vertex2 = function(x, y) end
    newRenderer.vertex3 = function(x, y, z) end
    newRenderer.glend = function() end

    return newRenderer
end

return Renderer