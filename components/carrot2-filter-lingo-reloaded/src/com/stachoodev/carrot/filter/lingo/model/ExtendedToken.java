/*
 * ExtendedToken.java Created on 2004-05-14
 */
package com.stachoodev.carrot.filter.lingo.model;

import java.util.*;

import com.dawidweiss.carrot.core.local.linguistic.tokens.*;
import com.dawidweiss.carrot.util.common.*;

/**
 * A class that wraps around any
 * {@link com.dawidweiss.carrot.core.local.linguistic.tokens.Token}and adds to
 * it support for storing/retrieving properties.
 * 
 * @author stachoo
 */
public class ExtendedToken implements Token, PropertyProvider
{
    /** Token delegate */
    private Token token;

    /** Properties of this token */
    private PropertyHelper propertyHelper;

    /** Document frequency */
    public static final String PROPERTY_DF = "df";

    /** Term frequency across the whole collection */
    public static final String PROPERTY_TF = "tf";

    /** Inverse document frequency factor */
    public static final String PROPERTY_IDF = "idf";

    /** The index of this token on the selected features list */
    public static final String PROPERTY_INDEX = "idx";

    /**
     * If present, for a generalized (i.e. stemmed) extended token returns all
     * original variants as a list of {@link ExtendedToken}s.
     */
    public static final String PROPERTY_ORIGINAL_TOKENS = "originalTokens";

    /**
     * Creates an ExtendedToken wrapped around the provided
     * {@link com.dawidweiss.carrot.core.local.linguistic.tokens.Token}.
     * 
     * @param token
     */
    public ExtendedToken(Token token)
    {
        this.token = token;

        // we don't expect ExtendedTokens not defining any properties, hence
        // no lazy initalization of the property container
        this.propertyHelper = new PropertyHelper();
    }

    /**
     * @return
     */
    public ExtendedTokenSequence asTokenSequence()
    {
        ExtendedTokenSequence extendedTokenSequence = asShallowTokenSequence(this);

        // Convert original tokens as well
        List originalTokens = (List) getProperty(PROPERTY_ORIGINAL_TOKENS);
        List originalTokenSequences = new ArrayList();
        for (Iterator tokens = originalTokens.iterator(); tokens.hasNext();)
        {
            ExtendedToken originalToken = (ExtendedToken) tokens.next();
            originalTokenSequences.add(asShallowTokenSequence(originalToken));
        }
        extendedTokenSequence.setProperty(
            ExtendedTokenSequence.PROPERTY_ORIGINAL_TOKEN_SEQUENCES,
            originalTokenSequences);

        return extendedTokenSequence;
    }

    /**
     * Does not copy original tokens onto original token sequences
     * 
     * @param extendedToken
     * @return
     */
    private ExtendedTokenSequence asShallowTokenSequence(
        ExtendedToken extendedToken)
    {
        ExtendedTokenSequence extendedTokenSequence = new ExtendedTokenSequence(
            new MutableTokenSequence(extendedToken));
        extendedTokenSequence.setProperty(ExtendedTokenSequence.PROPERTY_TF,
            getProperty(PROPERTY_TF));

        return extendedTokenSequence;
    }

    /**
     * Gets a value of a named property associated with this ExtendedToken.
     * 
     * @param propertyName
     * @return
     */
    public Object getProperty(String propertyName)
    {
        return propertyHelper.getProperty(propertyName);
    }

    /**
     * Sets a named property for this ExtendedToken.
     * 
     * @param propertyName
     * @param value
     * @return
     */
    public Object setProperty(String propertyName, Object property)
    {
        return propertyHelper.setProperty(propertyName, property);
    }

    /**
     * Returns a named <code>double</code> value associated with this
     * ExtendedToken. It is the responsibility of the programmer to assure that
     * the appropriate object casting succeeds.
     * 
     * @param propertName
     * @return
     */
    public double getDoubleProperty(String propertyName)
    {
        return propertyHelper.getDoubleProperty(propertyName);
    }

    /**
     * Sets a named <code>double</code> value for this ExtendedToken.
     * 
     * @param propertyName
     * @param value
     * @return
     */
    public Object setDoubleProperty(String propertyName, double value)
    {
        return propertyHelper.setDoubleProperty(propertyName, value);
    }

    /**
     * Returns a named <code>int</code> value associated with this
     * ExtendedToken. It is the responsibility of the programmer to assure that
     * the appropriate object casting succeeds.
     * 
     * @param propertyName
     * @return
     */
    public int getIntProperty(String propertyName)
    {
        return propertyHelper.getIntProperty(propertyName);
    }

    /**
     * Sets a named <code>int</code> value for this ExtendedToken.
     * 
     * @param propertyName
     * @param value
     * @return
     */
    public Object setIntProperty(String propertyName, int value)
    {
        return propertyHelper.setIntProperty(propertyName, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.dawidweiss.carrot.core.local.linguistic.tokens.Token#appendTo(java.lang.StringBuffer)
     */
    public void appendTo(StringBuffer buffer)
    {
        token.appendTo(buffer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return token.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object arg)
    {
        if (arg == this)
        {
            return true;
        }

        if (arg == null)
        {
            return false;
        }

        if (arg.getClass() != getClass())
        {
            return false;
        }
        else
        {
            return token.equals(((ExtendedToken) arg).token)
                && propertyHelper.equals(((ExtendedToken) arg).propertyHelper);

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return token.hashCode() + propertyHelper.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.dawidweiss.carrot.core.local.linguistic.tokens.Token#getImage()
     */
    public String getImage()
    {
        return token.getImage();
    }
}