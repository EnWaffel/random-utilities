--- @class NativeStream
stream = {}

--- @class NativeOutputStream
__out_stream = {
    ---@param b number
    write = function(b) end,
    ---@param bytes table<number>
    writeBytes = function(bytes) end,
    flush = function() end,
    close = function() end
}

---@class OutputStream
stream.output = {
    --- @param path string
    --- @return NativeOutputStream
    fromFile = function(path) return __out_stream end
}

stream.input = {
    ---
    --- Reads the data from the given InputStream.
    ---
    ---@return number the read byte
    read = function() return 0 end,
    close = function() end
}

return stream