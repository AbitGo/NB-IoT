
package com.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception Handling Tool Class
 *
 */
public class ExceptionUtil
{
    /**
     * line separator
     */
    public final static String LINE_SEPARATOR = "\r\n";
    
    /**
     *  Method that will get the exception stack information.
     * @param e
     *         Indicates a exception
     * @return
     */
    public static String getExceptionStackTrace(Throwable e)
    {
        String stackTrace = "";
        StringWriter writer = null;
        PrintWriter bw = null;
        if (e == null)
        {
            return "";
        }

        try
        {
            writer = new StringWriter();
            bw = new PrintWriter(writer);
            e.printStackTrace(bw);
            stackTrace = writer.getBuffer().toString();
        }
        catch (Exception e1)
        {
        }
        finally
        {
            if (writer != null)
            {
                try
                {
                    writer.close();
                }
                catch (Exception e2)
                {
                }
            }

            if (bw != null)
            {
                try
                {
                    bw.close();
                }
                catch (Exception e2)
                {
                }
            }
        }
        return stackTrace;
    }

    /**
     * Method that will get the exception stack information of a specified number of lines.
     * @param e
     *         Indicates a exception.
     * @param lineNum
     *         Indicates the number of stack lines to be printed.
     * @return
     */
    public static String getExceptionStackTrace(Throwable e, int lineNum)
    {
        if (e == null)
        {
            return "";
        }

        StringBuffer stackTrace = new StringBuffer(e.toString());
        StackTraceElement[] astacktraceelement = e.getStackTrace();
        int size = lineNum > astacktraceelement.length ? astacktraceelement.length
                : lineNum;

        for (int i = 0; i < size; i++)
        {
            stackTrace.append(LINE_SEPARATOR).append("\tat ")
                    .append(astacktraceelement[i]);
        }

        return stackTrace.toString();
    }
    
    /**
     * Method that will get the stack logs.
     * @param stackTraceElements
     *              Indicates the stack information.
     * @return
     */
    public static String getStackTraceLog(StackTraceElement[] stackTraceElements)
    {
        if (stackTraceElements == null)
        {
            return "";
        }

        StringBuffer stackTrace = new StringBuffer();
        int size = stackTraceElements.length;

        for (int i = 0; i < size; i++)
        {
            stackTrace.append(LINE_SEPARATOR).append("\tat ")
                    .append(stackTraceElements[i]);
        }

        return stackTrace.toString();
    }
    
    /**
     * Method that will get brief exception stack and only print the last five exception stack.
     * @param e
     *         Indicates a exception
     * @return
     */
    public static String getBriefExceptionStackTrace(Throwable e)
    {
        return getExceptionStackTrace(e, 5);
    }
}
