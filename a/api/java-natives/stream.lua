--- @class NativeStream
stream = {}

--- @class NativeOutputStream
__out_stream = {
    ---@param b number
    write = function(b) end,
    flush = function() end,
    close = function() end
}

---@class OutputStream
stream.output = {
    --- @param path string
    --- @return NativeOutputStream
    fromFile = function(path) end
}

stream.input = {
    ---
    --- Writes the given data to the OutputStream.
    ---
    ---@return string if succeeded false if failed
    read = function() end,
    close = function() end
}

return stream