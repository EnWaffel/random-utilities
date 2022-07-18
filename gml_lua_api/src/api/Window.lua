Window = {}
Window.__index = Window

function Window.new(renderer)
    local newWindow = {}
    setmetatable(newWindow, Window)

    newWindow.show = function() end
    newWindow.hide = function() end
    newWindow.setSize = function(width, height) end
    newWindow.setPosition = function(x, y) end
    newWindow.onRender = function() end -- render loop (60fps = render loop runs every 16ms)

    return newWindow
end

return Window;