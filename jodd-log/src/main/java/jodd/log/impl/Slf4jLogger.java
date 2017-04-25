// Copyright (c) 2003-present, Jodd Team (http://jodd.org)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

package jodd.log.impl;

import jodd.log.Logger;
import org.slf4j.spi.LocationAwareLogger;

/**
 * SLF4J logger adapter.
 */
public class Slf4jLogger implements Logger {

	private static final String FQCN = Slf4jLogger.class.getName();

	private final org.slf4j.Logger logger;
	private final LocationAwareLogger locationAwareLogger;

	public Slf4jLogger(org.slf4j.Logger logger) {
		this.logger = logger;
		if (logger instanceof LocationAwareLogger) {
			locationAwareLogger = (LocationAwareLogger) logger;
		}
		else {
			locationAwareLogger = null;
		}
	}

	@Override
	public String getName() {
		return logger.getName();
	}

	@Override
	public boolean isEnabled(Level level) {
		switch (level) {
			case TRACE: return logger.isTraceEnabled();
			case DEBUG: return logger.isDebugEnabled();
			case INFO: return logger.isInfoEnabled();
			case WARN: return logger.isWarnEnabled();
			case ERROR: return logger.isErrorEnabled();
			default:
				throw new IllegalArgumentException();
		}
	}

	@Override
	public void log(Level level, String message) {
		switch (level) {
			case TRACE: trace(message); break;
			case DEBUG: debug(message); break;
			case INFO: info(message); break;
			case WARN: warn(message); break;
			case ERROR: error(message); break;
		}
	}

	@Override
	public void setLevel(Level level) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public void trace(String message) {
		if (locationAwareLogger != null) {
			locationAwareLogger.log(
				null, FQCN, LocationAwareLogger.TRACE_INT, message, null, null);
		}
		else {
			logger.trace(message);
		}
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public void debug(String message) {
		if (locationAwareLogger != null) {
			locationAwareLogger.log(
				null, FQCN, LocationAwareLogger.DEBUG_INT, message, null, null);
		}
		else {
			logger.debug(message);
		}
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public void info(String message) {
		if (locationAwareLogger != null) {
			locationAwareLogger.log(
				null, FQCN, LocationAwareLogger.INFO_INT, message, null, null);
		}
		else {
			logger.info(message);
		}
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public void warn(String message) {
		if (locationAwareLogger != null) {
			locationAwareLogger.log(
				null, FQCN, LocationAwareLogger.WARN_INT, message, null, null);
		}
		else {
			logger.warn(message);
		}
	}

	@Override
	public void warn(String message, Throwable throwable) {
		if (locationAwareLogger != null) {
			locationAwareLogger.log(
				null, FQCN, LocationAwareLogger.WARN_INT, message, null, throwable);
		}
		else {
			logger.warn(message, throwable);
		}
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	@Override
	public void error(String message) {
		if (locationAwareLogger != null) {
			locationAwareLogger.log(
				null, FQCN, LocationAwareLogger.ERROR_INT, message, null, null);
		}
		else {
			logger.error(message);
		}
	}

	@Override
	public void error(String message, Throwable throwable) {
		if (locationAwareLogger != null) {
			locationAwareLogger.log(
				null, FQCN, LocationAwareLogger.ERROR_INT, message, null, throwable);
		}
		else {
			logger.error(message, throwable);
		}
	}
}